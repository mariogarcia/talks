@Grab('com.github.grooviter:gql-core:0.3.0')
@GrabExclude('org.codehaus.groovy:groovy-all')
import gql.DSL
import graphql.schema.DataFetchingEnvironment

RAFFLES = evaluate('gql-data-raffles.groovy' as File)

// 1. DEFINE data types
def Contestant = DSL.type('Contestant') {
    field 'name', GraphQLString
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
            type(list(Contestant))
            argument('max', GraphQLInt)
            fetcher { env ->
                RAFFLES.find().contestants.take(env.arguments.max)
            }
        }
    }
}


// 4. EXECUTE query
def query = '''
 {
   list(max: 4) {
    name
   }
 }
'''

def result = DSL
    .execute(Schema, query)
    .data

println result
