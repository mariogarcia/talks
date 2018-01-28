package bond.graphql

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import graphql.schema.GraphQLSchema

/**
 * Binds GraphQLSchema
 *
 * @since 0.1.0
 */
class SchemaModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(GraphQLSchema).toProvider(SchemaProvider)
  }
}
