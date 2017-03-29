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

  serverConfig {
    config -> config
    .yaml("helthix.yml")
    .require("", Map)
  }

  handlers {
    prefix('graphql') {
        all(createBindingHandler(Map))
        post(GraphQLHandler)
    }

    files {
      dir('static').indexFiles('index.html')
    }
  }
}
