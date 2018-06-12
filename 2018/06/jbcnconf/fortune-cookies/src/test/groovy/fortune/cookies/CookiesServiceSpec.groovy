package fortune.cookies

import java.util.concurrent.CompletionStage

import fortune.test.Fixtures
import gql.DSL
import graphql.ExecutionResult
import graphql.schema.GraphQLSchema
import org.pac4j.core.profile.UserProfile
import ratpack.exec.Promise
import ratpack.handling.Context
import ratpack.test.exec.ExecHarness
import spock.lang.AutoCleanup
import spock.lang.Specification
import spock.lang.Unroll

class CookiesServiceSpec extends Specification {

    @AutoCleanup
    ExecHarness execHarness = ExecHarness.harness()

    @Unroll
    void 'list cookies having offset: #offset and max: #max'() {
        given: 'mocked repository'
        def repository = Stub(CookiesRepository) {
            list(_) >> (1..max)
                .collect(Fixtures.&createCookieWithIndex)
                .subList(offset, max)
        }

        and: 'service under test'
        CookiesServiceImpl service = new CookiesServiceImpl(repository: repository)

        when: 'setting parameters'
        def arguments = [offset: offset, max: max]
        def environment = Fixtures.dataFetchingEnvironment(stubbedContext, arguments)

        and: 'invoking list method'
        List<Map> cookies = execHarness.yield({
            promise(service.listCookies(environment))
        }).value as List<Map>

        then: 'we should get expected number of cookies'
        cookies.size() == expected

        where: 'possible parameter values are'
        offset | max | expected
           0   |  5  |    5
           1   |  5  |    4
           2   |  5  |    3
           3   |  5  |    2
           4   |  5  |    1
           5   |  5  |    0
    }

    @Unroll
    void 'list cookies: schema validation is correct when offset: #offset and max: #max'() {
        given: 'a mocked service'
        CookiesService service = Mock(CookiesService)

        and: 'the GraphQL schemz'
        GraphQLSchema schema = DSL.mergeSchemas {
            byResource('schema/Cookie.graphql')
            byResource('schema/Security.graphql')
            byResource('schema/Schema.graphql') {
                mapType('Queries') {
                    link('cookies', service.&listCookies)
                }
            }
        }

        when: 'executing the query with the current parameters'
        String query = "{ cookies(offset: $offset, max: $max) { text } }"
        ExecutionResult result = DSL.execute(schema, query) {
            withVariables([max: max, offset: offset])
        }

        then: 'we should check if it is a valid case'
        result.errors.isEmpty() == valid

        where: 'parameter values can be'
         max    | offset |  valid
         null   |   0    |  false
          1     |  null  |  false
          1     |   1    |  true
    }

    /**
     * Returns an instance of {@link Context} containing an instance of
     * {@link UserProfile} in the registry via method {@link Context#get}
     *
     * @return an instance of {@link Context}}
     */
    Context getStubbedContext() {
        return Stub(Context) {
            get(UserProfile) >> Fixtures.userProfileWithUsername('john')
        }
    }

    /**
     * Converts a {@link CompletionStage} to a {@link Promise}
     *
     * @param stage a
     * @return a {@link Promise} that yields the same type the {@link CompletionStage} did
     */
    static <T> Promise<T> promise(CompletionStage<T> stage) {
        Promise.async({ downstream ->
            downstream.accept(stage)
        })
    }
}
