package bond.graphql

//import static gql.DSL.mergeSchemas
import gql.DSL
import graphql.schema.GraphQLSchema
import bond.system.SystemService
import bond.system.Types as SystemTypes

import javax.inject.Inject
import javax.inject.Provider

import bond.db.Queries

/**
 * Provides a singleton instance of the {@link GraphQLSchema} type
 *
 * @since 0.1.0
 */
class SchemaProvider implements Provider<GraphQLSchema> {

  @Inject
  SystemService systemService

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

        field('system') {
          type SystemTypes.GraphQLSystemHealth
          fetcher(systemService.&getSystemHealth)
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
