package fortune.graphql

import javax.inject.Inject
import javax.inject.Provider

import fortune.cookies.CookiesServiceImpl
import graphql.schema.GraphQLSchema
import gql.DSL

/**
 * Loads application's GraphQL Schema
 *
 * @since 0.1.0
 */
class SchemaProvider implements Provider<GraphQLSchema> {

  /**
   * Service to access fortune cookies
   *
   * @since 0.1.0
   */
  @Inject
  CookiesServiceImpl cookiesService

  @Override
  GraphQLSchema get() {
    return DSL.mergeSchemas {
      byResource('schema/Cookie.graphql')
      byResource('schema/Security.graphql')
      byResource('schema/Schema.graphql') {
        mapType('Queries') {
            link('cookies', cookiesService.&listCookies)
            link('randomCookie', cookiesService.&findRandomCookie)
        }
        mapType('Mutations') {
            link('create', cookiesService.&createCookie)
        }
      }
    }
  }
}