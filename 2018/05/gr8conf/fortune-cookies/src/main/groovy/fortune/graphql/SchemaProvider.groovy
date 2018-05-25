package fortune.graphql

import javax.inject.Inject
import javax.inject.Provider

import fortune.CookiesService
import fortune.security.instrumentation.AuthenticationService
import graphql.schema.GraphQLSchema
import gql.DSL

/**
 * @since 0.1.0
 */
class SchemaProvider implements Provider<GraphQLSchema> {

  /**
   * @since 0.1.0
   */
  @Inject
  CookiesService cookiesService

  /**
   * @since 0.1.0
   */
  @Inject
  AuthenticationService authenticationService

  @Override
  GraphQLSchema get() {
    return DSL.mergeSchemas {
      byResource('schema/Cookie.graphql')
      byResource('schema/Security.graphql')
      byResource('schema/Schema.graphql') {
        mapType('Queries') {
            link('login', authenticationService.&login)
            link('randomCookie', cookiesService.&findRandomCookie)
        }
      }
    }
  }
}