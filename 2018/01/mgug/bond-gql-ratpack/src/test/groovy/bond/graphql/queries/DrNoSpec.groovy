package bond.graphql.queries

import gql.DSL
import graphql.schema.GraphQLSchema
import javax.inject.Inject
import spock.guice.UseModules
import spock.lang.Specification
import bond.graphql.GraphQLModule

@UseModules([GraphQLModule])
class DrNoSpec extends Specification {

  @Inject
  GraphQLSchema schema

  void 'find a film by year of release'() {
    when: 'executing query with parameters'
    // tag::parametrizedExecution[]
    def result = DSL
      .execute(schema, queryString, [year: year])
    // end::parametrizedExecution[]

    then: 'no errors'
    !result.errors

    and: 'we should get what we expected'
    result.data.filmByYear.title == expected

    where: 'cases are'
    year   | expected
    2015   | "SPECTRE"
    1962   | "DR. NO"

    // tag::parametrizedQuery[]
    queryString = '''
      query FindBondFilmByYear($year: String){
        filmByYear(year: $year) {
          year
          title
        }
      }
    '''
    // end::parametrizedQuery[]
  }
}
