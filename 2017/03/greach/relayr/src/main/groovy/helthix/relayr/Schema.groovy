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
        type nonNull(GraphQLString)
      }
      field('something', GraphQLBoolean)
      field('year') {
        description 'release date'
        type GraphQLString
      }
    }
  // end::filmType[]

  static final GraphQLObjectType ThemeSong =
    DSL.type('ThemeSong') {
      field 'title', GraphQLString
      field 'performedBy', GraphQLString
    }

  static final GraphQLObjectType BondGirl =
    DSL.type('BondGirl') {
      field 'character', GraphQLString
      field 'actress', GraphQLString
    }

  static final GraphQLObjectType Villain =
    DSL.type('Villain') {
      field 'character', GraphQLString
      field 'actor', GraphQLString
    }

  static final GraphQLObjectType CounterPart =
    DSL.type('CounterPart') {
      field 'character', GraphQLString
      field 'actor', GraphQLString
    }


    // tag::applicationSchema[]
  static final GraphQLObjectType Film =
    DSL.type('Film') {
      field 'title'       , nonNull(GraphQLString)
      field 'year'        , GraphQLInt
      field 'directedBy'  , GraphQLString
      field 'bond'        , GraphQLString
      field 'themeSong'   , list(ThemeSong)
      field 'bondGirls'   , list(BondGirl)
      field 'villains'    , list(Villain)
      field 'counterparts', list(CounterPart)
      field 'gadgets'     , list(GraphQLString)
      field 'vehicles'    , list(GraphQLString)
    }
    // end::applicationSchema[]
}
