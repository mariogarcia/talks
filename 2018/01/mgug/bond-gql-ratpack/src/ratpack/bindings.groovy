import static ratpack.groovy.Groovy.ratpack

import bond.graphql.SchemaModule
import bond.system.SystemModule
import gql.ratpack.GraphQLModule

ratpack {
  bindings {
    module SystemModule
    module SchemaModule
    module GraphQLModule
  }
}
