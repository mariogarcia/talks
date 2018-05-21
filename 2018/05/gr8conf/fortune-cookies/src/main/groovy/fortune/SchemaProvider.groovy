// tag::serviceInjected[]
package fortune

import javax.inject.Inject
import javax.inject.Provider
import graphql.schema.GraphQLSchema
import gql.DSL

class SchemaProvider implements Provider<GraphQLSchema> {

  @Inject
  CookiesService cookiesService // <1>

  @Override
  GraphQLSchema get() {
    return DSL.mergeSchemas {
      byResource('schema/Cookie.graphql')
      byResource('schema/Schema.graphql') {
        mapType('Queries') {
           link('randomCookie') { env ->
             return cookiesService.findRandomCookie(env)
           }
        }
      }
    }
  }
}
// end::serviceInjected[]

/**
// tag::sqlInjected[]
package fortune

import javax.inject.Inject
import javax.inject.Provider
import groovy.sql.Sql
import gql.DSL
import graphql.schema.GraphQLSchema

class SchemaProvider implements Provider<GraphQLSchema> {

  @Inject
  Sql sql // <1>

  @Override
  GraphQLSchema get() {
    return DSL.mergeSchemas {
      byResource('schema/Cookie.graphql')
      byResource('schema/Schema.graphql') {
        mapType('Queries') {
           link('randomCookie') { env ->
              String query = "SELECT * FROM cookies ORDER BY ID DESC LIMIT 1"

              sql.firstRow query// <2>
            }
        }
      }
    }
  }
}
// end::sqlInjected[]
**/

/**
// tag::dummy[]
package fortune

import javax.inject.Provider
import gql.DSL
import graphql.schema.GraphQLSchema

class SchemaProvider implements Provider<GraphQLSchema> {

  @Override
  GraphQLSchema get() {
    return DSL.mergeSchemas {
      byResource('schema/Cookie.graphql')
      byResource('schema/Schema.graphql') {
        mapType('Queries') { // <1>
            link('randomCookie') { env -> // <2>
                return [author: 'Anonymous', text: "Don't talk to strangers"] // <3>
            }
        }
      }
    }
  }
}
// end::dummy[]
**/
/**
// tag::initial[]
package fortune

import javax.inject.Provider
import gql.DSL
import graphql.schema.GraphQLSchema

class SchemaProvider implements Provider<GraphQLSchema> {

  @Override
  GraphQLSchema get() {
    return DSL.mergeSchemas {
      byResource('schema/Cookie.graphql')
      byResource('schema/Schema.graphql')
    }
  }
}
// end::initial[]
**/
