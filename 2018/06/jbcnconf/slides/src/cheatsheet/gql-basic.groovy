@Grab('com.github.grooviter:gql-core:0.3.0')
@GrabExclude('org.codehaus.groovy:groovy-all')
import gql.DSL
import graphql.schema.DataFetchingEnvironment

// 1. DATA TYPES
def Contestant = null // name, age

// 2. SCHEMA
def Schema = null // list(max)

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












// UTILS
// ------------------------------
// ------------------------------
// ------------------------------
List<Map> getContestants(DataFetchingEnvironment env) {
    return evaluate('gql-data-raffles.groovy' as File)
            .find()
            .contestants
            .take(env.arguments.max)
}

import groovy.json.JsonOutput
println(JsonOutput.prettyPrint(JsonOutput.toJson(result)))