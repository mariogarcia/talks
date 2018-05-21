@Grapes([
  @Grab('io.ratpack:ratpack-groovy:1.5.1'),
  @Grab('com.github.grooviter:gql-ratpack:0.2.0')
])
import static ratpack.groovy.Groovy.ratpack

import gql.DSL
import gql.ratpack.GraphQLModule
import gql.ratpack.GraphQLHandler
import gql.ratpack.GraphiQLHandler

RAFFLES = evaluate('gql-data-raffles.groovy' as File)

// 1. LINK SCHEMA
def Schema = DSL.mergeSchemas {
    byURI(new File('Greach.graphql').toURI()) {
        mapType('Queries') {
            link('winners') { env ->
                return RAFFLES
                    .find { it.id == env.arguments.raffleId }
                    .contestants
                    .sort { a, b -> new Random().nextInt() }
                    .take(env.arguments.noWinners)
            }
        }
    }
}

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
