package bond.graphql

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import graphql.schema.GraphQLSchema

/**
 *
 */
class HandlerModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(Handler).in(Scopes.SINGLETON)
    bind(GraphQLSchema).toProvider(SchemaProvider)
  }
}
