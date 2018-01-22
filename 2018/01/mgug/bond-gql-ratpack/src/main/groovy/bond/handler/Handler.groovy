package bond.handler

import static gql.DSL.execute
import static ratpack.jackson.Jackson.json

import graphql.ExecutionResult
import graphql.schema.GraphQLSchema
import ratpack.exec.Blocking
import ratpack.handling.Context

import javax.inject.Inject

/**
 * GraphQL endpoint graphql
 *
 * @since 0.1.0
 */
class Handler implements ratpack.handling.Handler {

  @Inject
  GraphQLSchema schema

  // tag::handler[]
  @Override
  void handle(Context ctx) throws Exception {
    def payload = ctx.get(Map)
    def query = payload.query
    def params = payload.variables as Map<String,Object>

    Blocking.get {
      execute(schema, "$query", params)
    }.then { ExecutionResult result ->
      ctx.render(json([data: result.errors ?: result.data]))
    }
  }
  // end::handler[]
}
