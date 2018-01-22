import static ratpack.groovy.Groovy.ratpack

import ratpack.groovy.sql.SqlModule

import bond.system.SystemModule
import bond.graphql.HandlerModule

ratpack {
  bindings {
    module SystemModule
    module HandlerModule
  }
}
