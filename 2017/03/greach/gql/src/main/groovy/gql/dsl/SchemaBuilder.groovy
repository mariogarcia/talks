package gql.dsl

import graphql.schema.GraphQLSchema
import graphql.schema.GraphQLObjectType

/**
 * Builds a new {@link GraphQLSchema}
 * <br/>
 * <pre><code class="groovy">
 * GraphQLSchema schema = DSL.schema {
 *     queries {
 *         type('helloWorldQuery') {
 *             fields {
 *                 field('hello') {
 *                     type GraphQLString
 *                     staticValue 'world'
 *                 }
 *             }
 *         }
 *     }
 * }
 * </code></pre>
 *
 * @since 0.1.0
 */
class SchemaBuilder {

  GraphQLSchema.Builder builder = GraphQLSchema.newSchema()

  /**
   * Declares queries for the current schema
   * <br/>
   * <pre><code>
   * DSL.schema {
   *     queries {
   *         //...
   *     }
   * }
   * </code></pre>
   *
   * @param dsl
   * @return
   * @since 0.1.0
   */
  SchemaBuilder queries(@DelegatesTo(QueriesBuilder) Closure<QueriesBuilder> dsl) {
    Closure<QueriesBuilder> clos = dsl.clone() as Closure<QueriesBuilder>
    QueriesBuilder builderSource = new QueriesBuilder()
    QueriesBuilder builderResult = builderSource.with(clos) ?: builderSource
    List<GraphQLObjectType> qurs = builderResult.build()

    qurs.each(addQueryToBuilder(builder))

    return this
  }

  private Closure<GraphQLSchema.Builder> addQueryToBuilder(GraphQLSchema.Builder builder) {
    return { GraphQLObjectType type -> builder.query(type) }
  }

  /**
   * Returns {@GraphQLSchema} built by the builder
   *
   * @return an instance of {@link GraphQLSchema}
   * @since 0.1.0
   */
  GraphQLSchema build() {
    return builder.build()
  }

  /**
   * This builder acts as a proxy for:
   *
   * <ul>
   *   <li>{@link ObjectTypeBuilder}</li>
   * </ul>
   *
   * This way the api user will have DSL autodiscovery for types,
   * fields...etc from the {@link gql.DSL#schema} method.
   *
   * @since 0.1.0
   */
  static class QueriesBuilder {

    List<GraphQLObjectType> queries = []

    /**
     * Creates a new type within the underlying schema
     *
     * @param name name of the type
     * @param dsl the type dsl definition
     * @return the current builder instance
     * @since 0.1.0
     */
    QueriesBuilder type(String name, @DelegatesTo(ObjectTypeBuilder) Closure<ObjectTypeBuilder> dsl) {
      Closure<ObjectTypeBuilder> clos = dsl.dehydrate().clone() as Closure<ObjectTypeBuilder>
      ObjectTypeBuilder builderSource = new ObjectTypeBuilder().name(name)
      ObjectTypeBuilder builderResult = builderSource.with(clos) ?: builderSource

      queries << builderResult.build()
      return this
    }

    /**
     * Return the list of types defined in this fragment of the dsl
     *
     * @return a list of defined types of type {@link GraphQLObjectType}
     * @since 0.1.0
     */
    List<GraphQLObjectType> build() {
      return queries
    }
  }
}
