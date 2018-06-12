@Grapes([
  @Grab('io.ratpack:ratpack-groovy:1.5.1'),
  @Grab('com.github.grooviter:gql-core:0.3.3'),
  @Grab('com.github.grooviter:gql-ratpack:0.3.3')
])
import static ratpack.groovy.Groovy.ratpack

import gql.DSL
import gql.ratpack.GraphQLHandler
import gql.ratpack.GraphQLModule
import gql.ratpack.GraphiQLHandler
import graphql.schema.DataFetchingEnvironment

// 1. LINK SCHEMA
def Schema = null

// 2. CONFIGURE RATPACK
ratpack {
    bindings {
      module GraphQLModule

      bindInstance Schema
    }
    handlers {
        post('graphql', GraphQLHandler)
        get('graphql/browser', GraphiQLHandler)
    }
}








// 3. PROPER IMPLEMENTATION
List<Map> getWinners(DataFetchingEnvironment  env) {
    def contestants = evaluate('gql-data-raffles.groovy' as File)
            .find { it.id == env.arguments.raffleId }
            .contestants

    Collections.shuffle(contestants)

    return contestants.take(env.arguments.noWinners)
}