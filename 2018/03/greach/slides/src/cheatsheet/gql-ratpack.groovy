@Grapes([
  @Grab('io.ratpack:ratpack-groovy:1.5.1'),
  @Grab('org.slf4j:slf4j-simple:1.7.25'),
  @Grab('com.github.grooviter:gql-ratpack:0.2.0')
])
import static ratpack.groovy.Groovy.ratpack

import gql.DSL
import gql.ratpack.GraphQLModule
import gql.ratpack.GraphQLHandler
import gql.ratpack.GraphiQLHandler

CONTESTANTS = evaluate('gql-data-contestants.groovy' as File)
RAFFLES = evaluate('gql-data-raffles.groovy' as File)

def Contestant = DSL.type('Contestant') {
    field 'name', GraphQLString
}
def Raffle = DSL.type('Raffle') {
    field 'id', GraphQLInt
    field 'title', GraphQLString
    field 'contestants', list(Contestant)
}

def Schema = DSL.schema {
    queries {
        field('contestants') {
            type list(Contestant)
            argument 'max', nonNull(GraphQLInt)
            fetcher { env ->
                CONTESTANTS.take(env.arguments.max)
            }
        }
    }
}

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
