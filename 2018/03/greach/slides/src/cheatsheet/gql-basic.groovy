@Grab('com.github.grooviter:gql-core:0.2.0')
import gql.DSL

RAFFLES = evaluate('gql-data-raffles.groovy' as File)

def Contestant = null
def Raffle = null
def Schema = null

def query = '''
 {


 }
'''

def result = DSL
    .execute(Schema, query)
    .data