package helthix.relayr

import static ratpack.jackson.Jackson.json

import ratpack.handling.Handler
import ratpack.handling.Context

import javax.inject.Inject

import graphql.GraphQL
import graphql.schema.GraphQLSchema

/**
 * @since 0.1.0
 */
class GraphQLHandler implements Handler {

  // Idea: authorization could be applied over schema visibility
  // GraphQLSchema schema = schemaService.getSchemaForAuthorities(user.authorities)
  @Inject
  GraphQLSchema schema

  @Override
  void handle(final Context ctx) {
    Map<String,Object> payload = ctx.get(Map)
    Map<String,Object> results = new GraphQL(schema)
      .execute("${payload.query}", null, "${payload.variables}")
      .getData() as Map<String,Object>

    ctx.render json(results)
  }
}
