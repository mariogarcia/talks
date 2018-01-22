import static ratpack.groovy.Groovy.ratpack

import ratpack.server.ServerConfigBuilder
import bond.config.AppConfig
import bond.cors.CorsHandler
import bond.graphql.Utils
import bond.graphql.Handler

/**
 *
 */
ratpack {

  serverConfig { ServerConfigBuilder config ->
    config
      .port(8888)
      .yaml("newsdomaths.yml")
      .require("", AppConfig)
  }

  handlers {
    all(new CorsHandler())
    all(Utils.createBindingHandler(Map))
    prefix('graphql') {
      post(Handler)
    }
    files {
      dir('static').indexFiles('index.html')
    }
  }
}
