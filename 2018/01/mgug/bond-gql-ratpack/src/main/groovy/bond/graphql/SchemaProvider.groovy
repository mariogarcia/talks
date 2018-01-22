package bond.graphql

import static gql.DSL.mergeSchemas

import graphql.schema.GraphQLSchema

import javax.inject.Inject
import javax.inject.Provider


/**
 * Provides a singleton instance of the {@link GraphQLSchema} type
 *
 * @since 0.1.0
 */
class SchemaProvider implements Provider<GraphQLSchema> {

  @Override
  GraphQLSchema get() {
    return null
  }
}
