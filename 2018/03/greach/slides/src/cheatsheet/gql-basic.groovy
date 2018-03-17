@Grab('com.github.grooviter:gql-core:0.2.0')
import gql.DSL

RAFFLES = evaluate('gql-data-raffles.groovy' as File)

// 1. DEFINE data types
def Contestant = null
def Raffle = null

// 2 & 3. DEFINE queries & schema
def Schema = null

// 4. EXECUTE query
def query = '''
 {


 }
'''

def result = DSL
    .execute(Schema, query)
    .data
