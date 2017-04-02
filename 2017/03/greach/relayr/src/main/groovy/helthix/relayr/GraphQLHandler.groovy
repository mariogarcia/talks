package helthix.relayr

import graphql.ExecutionResult
import ratpack.exec.Blocking

import static ratpack.jackson.Jackson.json

import javax.inject.Inject
import ratpack.handling.Handler
import ratpack.handling.Context
import graphql.schema.GraphQLSchema

import gql.DSL

/**
 * @since 0.1.0
 */
// tag::handler[]
class GraphQLHandler implements Handler {

  @Inject
  GraphQLSchema schema

  @Override
  void handle(final Context ctx) {
    def payload = ctx.get(Map) // JSON request => Map

    Blocking.get {
      DSL.execute(schema, "${payload.query}", payload.variables as Map<String,Object>)
    }.then { ExecutionResult result ->
      def response = [data: result.errors ?: result.data]

      ctx.render json(response)
    }
  }
}
// end::handler[]
