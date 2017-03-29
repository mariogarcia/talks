package helthix.relayr

import spock.lang.Specification
import gql.DSL
import graphql.schema.GraphQLSchema
import graphql.schema.GraphQLObjectType
import bond.db.Queries

class QueryExecutionSpec extends Specification {

  void 'execute last film query'() {
    when: 'executing the query'
    // tag::executeSimpleQuery[]
    def result = DSL
      .execute(new GraphQLModule().schema, queryString)
      .data as Map<String,Object>
    // end::executeSimpleQuery[]

    then: 'we should get the expected movie'
    result == [lastFilm: [year: 2015, title: 'SPECTRE']]

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
}
