package helthix.relayr

import gql.DSL
import graphql.schema.GraphQLSchema
import bond.db.Queries
import spock.guice.UseModules
import spock.lang.Specification
import javax.inject.Inject

@UseModules([GraphQLModule])
class QueryExecutionSpec extends Specification {

  @Inject
  GraphQLSchema schema

  void 'find last film'() {
    when: 'executing the query'
    // tag::executeSimpleQuery[]
    def result = DSL
      .execute(schema, queryString)
      .data as Map<String,Object>
    // end::executeSimpleQuery[]

    then: 'we should get the expected movie'
    result == [lastFilm: [year: "2015", title: 'SPECTRE']]

    where: 'the queryString literal is'
    // tag::simpleQuery[]
    queryString = '''
      {
        lastFilm {
          year
          title
        }
      }
     '''
    // end::simpleQuery[]
  }

  void 'find a film by year of release'() {
    when: 'executing query with parameters'
    // tag::parametrizedExecution[]
    Map<String,Object> result = DSL
      .execute(schema, queryString, [year: year])
      .data
    // end::parametrizedExecution[]

    then: 'we should get what we expected'
    result.byYear.title == expected

    where: 'cases are'
    year   | expected
    "2015" | "SPECTRE"
    "1962" | "DR. NO"

    // tag::parametrizedQuery[]
    queryString = '''
      query FindBondFilmByYear($year: String){
        byYear(year: $year) {
          year
          title
        }
      }
    '''
    // end::parametrizedQuery[]
  }
}
