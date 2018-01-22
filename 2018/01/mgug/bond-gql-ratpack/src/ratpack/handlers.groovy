import static ratpack.groovy.Groovy.ratpack

import ratpack.server.ServerConfigBuilder
import bond.config.AppConfig
import bond.cors.CorsHandler
import bond.handler.Utils
import bond.handler.Handler

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

  handlers {
    all(new CorsHandler())
    all(Utils.createBindingHandler(Map))

    // tag::graphql[]
    prefix('graphql') {
      post(Handler)  // GraphQL
    }
    files {
      dir('static').indexFiles('index.html') // GraphiQL
    }
    // end::graphql[]
  }
}
