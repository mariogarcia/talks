import static ratpack.groovy.Groovy.ratpack

import bond.graphql.SchemaModule
import gql.ratpack.GraphQLModule

ratpack {
  bindings {
    module SchemaModule
    module GraphQLModule
  }
}
