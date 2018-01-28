import static ratpack.groovy.Groovy.ratpack

import ratpack.server.ServerConfigBuilder

import bond.config.AppConfig
import bond.cors.CorsHandler

import gql.ratpack.GraphQLHandler
import gql.ratpack.GraphiQLHandler

/**
 * Handler mappings
 */
ratpack {
  serverConfig { ServerConfigBuilder config ->
    config
      .port(8888)
      .yaml("bond.yml")
      .require("", AppConfig)
  }

  // tag::graphql[]
  handlers {
    all(new CorsHandler())

    prefix('graphql') {
      post(GraphQLHandler)
      prefix('browser') {
        get(GraphiQLHandler)
      }
    }
  }
  // end::graphql[]
}
