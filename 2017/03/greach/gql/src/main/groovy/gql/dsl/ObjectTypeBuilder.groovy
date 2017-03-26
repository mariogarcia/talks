package gql.dsl

import graphql.schema.GraphQLObjectType
import graphql.schema.GraphQLInterfaceType
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.GraphQLScalarType
import graphql.Scalars

/**
 * @since 0.1.0
 */
class ObjectTypeBuilder {

  GraphQLObjectType.Builder type = GraphQLObjectType.newObject()

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
  ObjectTypeBuilder fields(@DelegatesTo(FieldsBuilder) Closure<FieldsBuilder> fields) {
    Closure<FieldsBuilder> clos = fields.clone() as Closure<FieldsBuilder>
    FieldsBuilder builderSource = new FieldsBuilder()
    FieldsBuilder builderResult = builderSource.with(clos) ?: builderSource

    this.type = type.fields(builderResult.build())
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
  static class FieldsBuilder {

    /**
     * @since 0.1.0
     */
    List<GraphQLFieldDefinition> fields = []

    /**
     * @since 0.1.0
     */
    FieldsBuilder field(String name, @DelegatesTo(FieldBuilder) Closure<FieldBuilder> dsl) {
      Closure<FieldBuilder> clos = dsl.dehydrate().clone() as Closure<FieldBuilder>
      FieldBuilder builderSource = new FieldBuilder().name(name)
      FieldBuilder builderResult = builderSource.with(clos) ?: builderSource

      fields << builderResult.build()
      return this
    }

    /**
     * @since 0.1.0
     */
    List<GraphQLFieldDefinition> build() {
      return fields
    }
  }

  /**
   * @since 0.1.0
   */
  @SuppressWarnings('PropertyName')
  static class FieldBuilder {

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
