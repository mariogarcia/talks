package gql

import gql.dsl.SchemaBuilder
import gql.dsl.ObjectTypeBuilder

import graphql.GraphQL
import graphql.ExecutionResult
import graphql.schema.GraphQLSchema
import graphql.schema.GraphQLObjectType

/**
 * Functions in this class ease the creation of different GraphQL
 * schema elements
 *
 * @since 0.1.0
 */
final class DSL {

  /**
   * Builds the estructure of a {@link ObjectType} using a closure
   * which delegates to {@link ObjectTypeBuilder}
   * <br/>
   * <pre><code class="groovy">
   * GraphQLObjectType helloQuery = DSL.type('helloWorldQuery') {
   *     fields {
   *         field('hello') {
   *             type GraphQLString
   *             staticValue 'world'
   *         }
   *     }
   * }
   * </code></pre>
   * <br/>
   * When specifying scalar types in a field it's not neccessary to
   * import basic scalar types, the DSL already knows basic scalar
   * types.
   *
   * @param name name of the object to build
   * @param dsl dsl delegating its calls to an instance of type {@link
   * ObjectTypeBuilder}
   * @return an instance of {@link ObjectType}
   * @since 0.1.0
   */
  static GraphQLObjectType type(String name, @DelegatesTo(ObjectTypeBuilder) Closure<ObjectTypeBuilder> dsl) {
    Closure<ObjectTypeBuilder> clos = dsl.clone() as Closure<ObjectTypeBuilder>
    ObjectTypeBuilder builderSource = new ObjectTypeBuilder().name(name)
    ObjectTypeBuilder builderResult = builderSource.with(clos) ?: builderSource

    return builderResult.build()
  }

  /**
   * Builds a new {@link GraphQLSchema} using a closure which
   * delegates to {@link SchemaBuilder}
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
   * @param dsl closure with the schema elements. It should follow
   * rules of {@link SchemaBuilder}
   * @return an instance of {@link SchemaBuilder}
   * @since 0.1.0
   */
  static GraphQLSchema schema(@DelegatesTo(SchemaBuilder) Closure<SchemaBuilder> dsl) {
    Closure<SchemaBuilder> clos = dsl.clone() as Closure<SchemaBuilder>
    SchemaBuilder builderSource = new SchemaBuilder()
    SchemaBuilder builderResult = builderSource.with(clos) ?: builderSource

    return builderResult.build()
  }

  /**
   * Executes the query against the underlying schema
   * <br/>
   * <pre><code class="groovy">
   * GraphQLObjectType filmType = DSL.type('film') {
   *   fields {
   *     field('title') {
   *       description 'title of the film'
   *       type GraphQLString
   *     }
   *   }
   * }
   *
   * GraphQLSchema schema = DSL.schema {
   *   queries {
   *     type('lastBondFilm') {
   *       description'get last Bond film'
   *       fields {
   *         field('lastFilm') {
   *           description'last film'
   *           type filmType
   *           fetcher Queries.&findLastFilm
   *         }
   *       }
   *     }
   *   }
   * }
   *
   * Map<String,Map> dataMap = DSL
   *  .execute(schema, '{ lastFilm { title } }')
   *  .data
   * </code></pre>
   *
   * @param schema the schema defining the query
   * @param query the query string
   * @return an instance of {@link ExecutionResult}
   * @since 0.1.0
   */
  static ExecutionResult execute(GraphQLSchema schema, String query) {
    return new GraphQL(schema).execute(query)
  }
}
