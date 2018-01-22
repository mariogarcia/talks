package bond.graphql

import gql.DSL
import graphql.schema.GraphQLObjectType

/**
 * Defines GraphQL types using code
 *
 * @since 0.1.0
 */
class Types {

  // tag::Film[]
  static GraphQLObjectType Film = DSL.type('Film') {
    field 'title', nonNull(GraphQLString)
    field 'year', GraphQLInt
  }
  // end::Film[]
}
