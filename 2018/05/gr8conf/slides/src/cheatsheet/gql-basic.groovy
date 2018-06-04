@Grab('com.github.grooviter:gql-core:0.3.0')
@GrabExclude('org.codehaus.groovy:groovy-all')
import gql.DSL
import graphql.schema.DataFetchingEnvironment
// 1. DEFINE data types
// ====> Contestant(name, age)
def Contestant = DSL.type('Contestant') {
    field 'name', GraphQLString
    field 'age', GraphQLFloat
}
// ====> Raffle(title, contestants)
def Raffle = DSL.type('Raffle') {
    field 'title', GraphQLString
    field 'contestants', list(Contestant)
}
// 2. DEFINE SCHEMA
// ====> list(list(Contestant))
def Schema = DSL.schema {
    queries {
        field('list'){
            type(list(Contestant))
            argument('max', nonNull(GraphQLInt))
            fetcher(this.&getContestants)
        }
    }
}

List<Map> getContestants(DataFetchingEnvironment env) {
    return evaluate('gql-data-raffles.groovy' as File)
        .find()
        .contestants
        .take(env.arguments.max)
}

// 3. EXECUTE query
// 4. VALIDATION ERROR (optional)
def query = '''
 {
   list(max: 3) {
    name
    age
   }
 }
'''

def result = DSL
    .execute(Schema, query)
    .with {
       return [data: it.data, errors: it.errors]
    }

println result

