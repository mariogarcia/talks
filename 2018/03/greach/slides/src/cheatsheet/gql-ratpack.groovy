@Grapes([
  @Grab('io.ratpack:ratpack-groovy:1.5.1'),
  @Grab('com.github.grooviter:gql-ratpack:0.2.0')
])
import static ratpack.groovy.Groovy.ratpack

import gql.DSL
import gql.ratpack.GraphQLModule
import gql.ratpack.GraphQLHandler
import gql.ratpack.GraphiQLHandler

def raffles = evaluate('gql-data-raffles.groovy' as File)
def fetcher = { env -> }

def Schema = DSL.mergeSchemas {
    byURI(new File('Greach.graphql').toURI()) {
        mapType('Queries') {
            link('winners', fetcher)
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
