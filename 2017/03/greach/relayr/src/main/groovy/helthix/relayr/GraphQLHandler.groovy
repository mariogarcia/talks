package helthix.relayr

import static ratpack.jackson.Jackson.json

import javax.inject.Inject
import ratpack.handling.Handler
import ratpack.handling.Context
import graphql.schema.GraphQLSchema

import gql.DSL

/**
 * @since 0.1.0
 */
class GraphQLHandler implements Handler {

  @Inject
  GraphQLSchema schema

  @Override
  void handle(final Context ctx) {
    def payload = ctx.get(Map)
    def results = DSL.execute(schema, payload.query, payload.variables)

    ctx.render json(results.errors ?: results.data)
  }
}
