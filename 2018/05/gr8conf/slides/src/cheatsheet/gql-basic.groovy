@Grab('com.github.grooviter:gql-core:0.3.0')
@GrabExclude('org.codehaus.groovy:groovy-all')
import gql.DSL
import graphql.schema.DataFetchingEnvironment

RAFFLES = evaluate('gql-data-raffles.groovy' as File)

// 1. DEFINE data types
def Contestant = DSL.type('Contestant') {
    field 'name', GraphQLString
    field 'age', GraphQLFloat
}

def Raffle = DSL.type('Raffle') {
    field 'id', GraphQLString
    field 'title', GraphQLString
    field 'contestants', list(Contestant)
}

// 2. DEFINE SCHEMA

def Schema = DSL.schema {
    queries {
        field('list') {
            type list(Contestant)
            argument 'max', nonNull(GraphQLInt)
            fetcher(this.&getContestants)
        }
    }
}

List<Map> getContestants(DataFetchingEnvironment env) {
    return RAFFLES
        .find()
        .contestants
        .take(env.arguments.max)
}

// 4. EXECUTE query
def query = '''
 {
   list(max: 2) {
    name
    age
   }
 }
'''

def result = DSL.execute(Schema, query)

println result.data ?: result.errors
