package bond.schema

import graphql.GraphQL
import graphql.schema.GraphQLSchema
import spock.lang.Specification

class BondQueryExecutionSpec extends Specification {

  void 'execute last film query'() {
    setup: 'build the schema'
    GraphQLSchema schema = new BondSchemaBuilder().buildSchema()

    when: 'executing the query'
    // tag::executeSimpleQuery[]
    Map<String,Object> result = new GraphQL(schema) // <1>
    .execute(query,                                 // <2>
             null,                                  // <3>
             "")                                    // <4>
    .getData() as Map<String,Object>
    // end::executeSimpleQuery[]

    then: 'we should get the expected movie'
    result == [lastFilm: [year: 2015, title: 'SPECTRE']]

    where: 'the query literal is'
    // tag::simpleQuery[]
    query = '''
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
