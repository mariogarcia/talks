package helthix.relayr

import helthix.relayr.Schema

import com.google.inject.Scopes
import com.google.inject.AbstractModule
import com.google.inject.Provides
import com.google.inject.Singleton

import gql.DSL
import bond.db.Queries
import graphql.schema.GraphQLSchema

/**
 * @since 0.1.0
 */
class GraphQLModule  extends AbstractModule {

  @Override
  void configure() {
    bind(GraphQLHandler).in(Scopes.SINGLETON)
  }

  @Provides
  @Singleton
  GraphQLSchema getSchema() {
    // tag::appQueries[]
    return DSL.schema {
      query('Queries') {

        field('lastFilm') {
          type Schema.Film
          fetcher Queries.&findLastFilm
        }

        field('byYear') {
          type Schema.Film
          fetcher Queries.&findByYear
          argument('year') {
            type GraphQLString
          }
        }

        field('byBondActorNameLike') {
          type list(Schema.Film)
          fetcher Queries.&byBondActorNameLike
          argument('name') {
            type GraphQLString
          }
        }
      }
    }
    // end::appQueries[]
  }

  GraphQLSchema getExampleSchema() {
    // tag::queryType[]
    return DSL.schema {
      query('Queries') {
        field('lastFilm') {
          type Schema.FilmType
          fetcher Queries.&findLastFilm
        }
        field('byYear') {
          type Schema.FilmType
          fetcher Queries.&findByYear
          argument('year') {
            type GraphQLString
          }
        }
      }
    }
    // end::queryType[]
  }
}
