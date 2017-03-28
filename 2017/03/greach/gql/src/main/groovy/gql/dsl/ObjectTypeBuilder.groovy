package gql.dsl

import graphql.Scalars
import graphql.schema.GraphQLType
import graphql.schema.DataFetcher
import graphql.schema.GraphQLOutputType
import graphql.schema.GraphQLObjectType
import graphql.schema.GraphQLInterfaceType
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.GraphQLScalarType
import graphql.schema.GraphQLList
import graphql.schema.GraphQLNonNull

/**
 * @since 0.1.0
 */
@SuppressWarnings('PropertyName')
class ObjectTypeBuilder {

  GraphQLObjectType.Builder type = GraphQLObjectType.newObject()

  GraphQLScalarType GraphQLInt = Scalars.GraphQLInt
  GraphQLScalarType GraphQLString = Scalars.GraphQLString
  GraphQLScalarType GraphQLLong = Scalars.GraphQLLong
  GraphQLScalarType GraphQLShort = Scalars.GraphQLShort
  GraphQLScalarType GraphQLByte = Scalars.GraphQLByte
  GraphQLScalarType GraphQLFloat = Scalars.GraphQLFloat
  GraphQLScalarType GraphQLBigInteger = Scalars.GraphQLBigInteger
  GraphQLScalarType GraphQLBigDecimal = Scalars.GraphQLBigDecimal
  GraphQLScalarType GraphQLBoolean = Scalars.GraphQLBoolean
  GraphQLScalarType GraphQLID = Scalars.GraphQLID
  GraphQLScalarType GraphQLChar = Scalars.GraphQLChar

  /**
   * @since 0.1.0
   */
  ObjectTypeBuilder name(String name) {
    this.type = type.name(name)
    return this
  }

  /**
   * @since 0.1.0
   */
  ObjectTypeBuilder description(String description) {
    this.type = type.description(description)
    return this
  }

  /**
   * @since 0.1.0
   */
  ObjectTypeBuilder addInterface(GraphQLInterfaceType interfaceType) {
    this.type = type.withInterface(interfaceType)
    return this
  }

  /**
   * @since 0.1.0
   */
  ObjectTypeBuilder addField(GraphQLFieldDefinition fieldDefinition) {
    this.type = type.field(fieldDefinition)
    return this
  }

  /**
   * @since 0.1.0
   */
  ObjectTypeBuilder interfaces(@DelegatesTo(InterfacesBuilder) Closure<InterfacesBuilder> interfaces) {
    Closure<InterfacesBuilder> clos = interfaces.clone() as Closure<InterfacesBuilder>
    InterfacesBuilder builderSource = new InterfacesBuilder()
    InterfacesBuilder builderResult = builderSource.with(clos) ?: builderSource

    this.type = type.withInterfaces(builderResult.build() as GraphQLInterfaceType[])
    return this
  }

  /**
   * @since 0.1.0
   */
  ObjectTypeBuilder field(String name, @DelegatesTo(FieldBuilder) Closure<FieldBuilder> dsl) {
    Closure<FieldBuilder> clos = dsl.clone() as Closure<FieldBuilder>
    FieldBuilder builderSource = new FieldBuilder().name(name)
    FieldBuilder builderResult = builderSource.with(clos) ?: builderSource

    this.type = type.field(builderResult.build())
    return this
  }

  /**
   * @since 0.1.0
   */
  ObjectTypeBuilder field(String name, GraphQLScalarType fieldType) {
    FieldBuilder builderSource = new FieldBuilder()
      .name(name)
      .description("description of field $name")
      .type(fieldType)

    this.type = type.field(builderSource.build())
    return this
  }

  ObjectTypeBuilder field(String name, GraphQLOutputType fieldType) {
    FieldBuilder builderSource = new FieldBuilder()
      .name(name)
      .description("description of field $name")
      .type(fieldType)

    this.type = type.field(builderSource.build())
    return this
  }

  /**
   * @since 0.1.0
   */
  GraphQLObjectType build() {
    return this.type.build()
  }

  /**
   * @since 0.1.0
   */
  static ObjectTypeBuilder newObject() {
    return new ObjectTypeBuilder()
  }

  /**
   * @since 0.1.0
   */
  static class FieldBuilder {

    GraphQLFieldDefinition.Builder builder = GraphQLFieldDefinition.newFieldDefinition()

    /**
     * @since 0.1.0
     */
    FieldBuilder name(String name) {
      builder.name(name)
      return this
    }

    /**
     * @since 0.1.0
     */
    FieldBuilder description(String description) {
      builder.description(description)
      return this
    }

    /**
     * @since 0.1.0
     */
    FieldBuilder type(GraphQLScalarType type) {
      builder.type(type)
      return this
    }

    /**
     * @since 0.1.0
     */
    FieldBuilder type(GraphQLOutputType type) {
      builder.type(type)
      return this
    }

    /**
     * @since 0.1.0
     */
    FieldBuilder nonNullType(GraphQLType type) {
      builder.type = new GraphQLNonNull(type)
      return this
    }

    /**
     * @since 0.1.0
     */
    FieldBuilder listType(GraphQLType type) {
      builder.type = new GraphQLList(type)
      return this
    }

    /**
     * @since 0.1.0
     */
    FieldBuilder fetcher(Closure<Object> fetcher) {
      builder.dataFetcher(fetcher as DataFetcher)
      return this
    }

    /**
     * @since 0.1.0
     */
    FieldBuilder fetcher(DataFetcher fetcher) {
      builder.dataFetcher(fetcher)
      return this
    }

    /**
     * @since 0.1.0
     */
    FieldBuilder staticValue(Object object) {
      builder.staticValue(object)
      return this
    }

    /**
     * @since 0.1.0
     */
    GraphQLFieldDefinition build() {
      return builder.build()
    }
  }

  /**
   * @since 0.1.0
   */
  static class InterfacesBuilder {

    /**
     * @since 0.1.0
     */
    List<GraphQLInterfaceType> interfaces = []

    /**
     * @since 0.1.0
     */
    InterfacesBuilder add(final GraphQLInterfaceType interfaceDefinition) {
      interfaces << interfaceDefinition
      return this
    }

    /**
     * @since 0.1.0
     */
    List<GraphQLInterfaceType> build() {
      return interfaces
    }
  }
}
