package bond.graphql.queries

import gql.DSL
import graphql.schema.GraphQLSchema
import javax.inject.Inject
import spock.guice.UseModules
import spock.lang.Specification
import bond.graphql.GraphQLModule

@UseModules([GraphQLModule])
class LastFilmSpec extends Specification {

  @Inject
  GraphQLSchema schema


  void 'find last film'() {
    // tag::executeSimpleQuery[]
    when: 'executing the query'
    // tag::test[]
    def result = DSL
      .execute(schema, queryString)
      .data as Map<String,Object>
    // end::test[]

    then: 'we should get the expected movie'
    result == [lastFilm: [year: 2015, title: 'SPECTRE']]

    where: 'the queryString literal is'
    // tag::query[]
    queryString = '''
      {
        lastFilm {
          year
          title
        }
      }
     '''
    // end::query[]
    // end::executeSimpleQuery[]
  }
}
