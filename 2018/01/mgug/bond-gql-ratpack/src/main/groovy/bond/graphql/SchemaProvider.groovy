package bond.graphql

//import static gql.DSL.mergeSchemas
import gql.DSL
import graphql.schema.GraphQLSchema

import javax.inject.Inject
import javax.inject.Provider

import bond.db.Queries
import bond.db.Contest

/**
 * Provides a singleton instance of the {@link GraphQLSchema} type
 *
 * @since 0.1.0
 */
class SchemaProvider implements Provider<GraphQLSchema> {

  @Override
  GraphQLSchema get() {
    // tag::queries[]
    return DSL.schema {
      queries('Queries') {
        field('lastFilm') {
          type Types.Film
          fetcher(Queries.&findLastFilm) // from db
        }

        field('filmByYear') {
          type Types.Film
          fetcher(Queries.&findByYear) // from db
          argument 'year', GraphQLString
        }
      }
    }
    // end::queries[]
  }
}


//  @Override
//  GraphQLSchema get() {
//    return DSL.mergeSchemas {
//      byResource('graphql/Schema.graphql')
//      byResource('graphql/Film.graphql')
//      byResource('graphql/System.graphql')
//      byResource('graphql/Queries.graphql') {
//        mapType('Queries') {
//          link('lastFilm', Queries.&findLastFilm)
//          link('filmByYear',Queries.&findByYear)
//          link('system') { env ->
//            [os: new bond.system.SystemService().getSystemOS()]
//          }
//        }
//      }
//    }
//  }
