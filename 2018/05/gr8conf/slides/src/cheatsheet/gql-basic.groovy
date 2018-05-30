@Grab('com.github.grooviter:gql-core:0.3.0')
@GrabExclude('org.codehaus.groovy:groovy-all')
import gql.DSL
import graphql.schema.DataFetchingEnvironment

RAFFLES = evaluate('gql-data-raffles.groovy' as File)

// 1. DEFINE data types
// ====> Contestant(name, age)
// ====> Raffle(title, contestants)

// 2. DEFINE SCHEMA
// ====> list(list(Contestant))

List<Map> getContestants(DataFetchingEnvironment env) {
    return RAFFLES
        .find()
        .contestants
        .take(env.arguments.max)
}

// 3. EXECUTE query
// 4. VALIDATION ERROR (optional)
def query = '''
 {
   list(max: 2) {
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

