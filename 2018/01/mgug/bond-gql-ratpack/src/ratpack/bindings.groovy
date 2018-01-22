import static ratpack.groovy.Groovy.ratpack

import ratpack.groovy.sql.SqlModule

import bond.system.SystemModule
import bond.handler.HandlerModule
import bond.graphql.GraphQLModule

ratpack {
  bindings {
    module SystemModule
    module HandlerModule
    module GraphQLModule
  }
}
