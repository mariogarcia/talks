import ratpack.server.ServerConfig

import static ratpack.groovy.Groovy.ratpack
import static helthix.utils.Handlers.createBindingHandler

import helthix.relayr.GraphQLHandler

/**
 *             _                        _
 *    _  _ _ _| |  _ __  __ _ _ __ _ __(_)_ _  __ _ ___
 *   | || | '_| | | '  \/ _` | '_ \ '_ \ | ' \/ _` (_-<
 *    \_,_|_| |_| |_|_|_\__,_| .__/ .__/_|_||_\__, /__/
 *                           |_|  |_|         |___/
 *
 * This configuration file ONLY contains handler mappings. It should
 * be used like an url mapping file.
 */
ratpack {

  serverConfig { config ->
    config
    .port(8080)
    .yaml("helthix.yml")
    .require("", Map)
  }

  // tag::handlers[]
  handlers {
    prefix('graphql') { // <1>
        all(createBindingHandler(Map))
        post(GraphQLHandler)
    }
    files {  // <2>
      dir('static').indexFiles('index.html')
    }
  }
// end::handlers[]
}
