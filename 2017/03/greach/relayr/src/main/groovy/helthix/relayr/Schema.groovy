package helthix.relayr

// tag::schema_imports[]
import gql.DSL
import graphql.schema.GraphQLObjectType
// end::schema_imports[]

class Schema {

  // tag::filmType[]
  static final GraphQLObjectType FilmType =
    DSL.type('film') {
      fields {
        field('title') {
          nonNullType GraphQLString
        }
        field('year') {
          type GraphQLInt
        }
      }
    }
  // end::filmType[]

}
