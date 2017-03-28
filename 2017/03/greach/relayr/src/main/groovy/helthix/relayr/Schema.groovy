package helthix.relayr

// tag::schema_imports[]
import gql.DSL
import graphql.schema.GraphQLObjectType
// end::schema_imports[]

class Schema {


  static final GraphQLObjectType FilmType =
  // tag::filmType[]
    DSL.type('Film') {
      field('title') {
        nonNullType GraphQLString
      }
      field('year') {
        type GraphQLInt
      }
    }
  // end::filmType[]

}
