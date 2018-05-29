import static ratpack.groovy.Groovy.ratpack
import static ratpack.pac4j.RatpackPac4j.authenticator
import static ratpack.pac4j.RatpackPac4j.requireAuth


import fortune.config.Config
import fortune.cookies.CookiesModule
import fortune.db.DbModule
import fortune.graphql.GraphQLExtraModule
import fortune.init.InitModule
import fortune.security.SecurityModule
import fortune.security.pac4j.Authenticator
import gql.ratpack.GraphQLModule
import gql.ratpack.GraphiQLHandler
import gql.ratpack.pac4j.GraphQLHandler
import org.pac4j.http.client.indirect.IndirectBasicAuthClient
import ratpack.groovy.sql.SqlModule
import ratpack.server.ServerConfigBuilder
import ratpack.session.SessionModule

ratpack {
  serverConfig { ServerConfigBuilder config -> // <1>
    config
      .yaml("cookies.yml")
      .require("", Config)
  }

  bindings {
    module GraphQLModule
    module GraphQLExtraModule
    module SqlModule
    module DbModule
    module InitModule
    module CookiesModule
    module SessionModule
    module SecurityModule
  }

  handlers {
    all(authenticator(new IndirectBasicAuthClient(registry.get(Authenticator))))

    prefix('graphql') {
      all(requireAuth(IndirectBasicAuthClient))

      post("", GraphQLHandler)
      get('browser', GraphiQLHandler)
    }
  }
}