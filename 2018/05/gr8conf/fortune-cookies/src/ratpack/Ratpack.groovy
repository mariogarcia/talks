import static ratpack.groovy.Groovy.ratpack

import fortune.config.Config
import fortune.FortuneModule
import gql.ratpack.GraphQLHandler
import gql.ratpack.GraphQLModule
import gql.ratpack.GraphiQLHandler
import ratpack.groovy.sql.SqlModule
import ratpack.server.ServerConfigBuilder

ratpack {
  serverConfig { ServerConfigBuilder config -> // <1>
    config
      .yaml("cookies.yml")
      .require("", Config)
  }

  bindings {
    module GraphQLModule
    module SqlModule
    module FortuneModule
  }

  handlers {
    post('graphql', GraphQLHandler)
    get('graphql/browser', GraphiQLHandler)
  }
}