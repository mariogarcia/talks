package bond.handler

import static gql.DSL.executeAsync
import static ratpack.jackson.Jackson.json

import javax.inject.Inject
import graphql.ExecutionResult
import graphql.schema.GraphQLSchema
import ratpack.exec.Blocking
import ratpack.exec.Promise
import ratpack.exec.Downstream
import ratpack.handling.Context

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

    Promise.async { Downstream downstream ->
      executeAsync(schema, "$query", params).thenApply { value ->
        downstream.success(value)
      }
    }.then { ExecutionResult result ->
      ctx.render(json(errors: result.errors, data: result.data))
    }
  }
  // end::handler[]
}
