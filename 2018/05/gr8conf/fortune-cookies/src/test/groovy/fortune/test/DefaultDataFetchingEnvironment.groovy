package fortune.test

import groovy.transform.NotYetImplemented

import graphql.execution.ExecutionId
import graphql.execution.ExecutionTypeInfo
import graphql.language.Field
import graphql.language.FragmentDefinition
import graphql.schema.DataFetchingEnvironment
import graphql.schema.DataFetchingFieldSelectionSet
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.GraphQLOutputType
import graphql.schema.GraphQLSchema
import graphql.schema.GraphQLType
import ratpack.handling.Context

class DefaultDataFetchingEnvironment implements DataFetchingEnvironment{

    Map<String, Object> arguments
    Context context

    @Override
    def <T> T getSource() {
        return null
    }

    @Override
    boolean containsArgument(String name) {
        return arguments.containsKey(name)
    }

    @Override
    def <T> T getArgument(String name) {
        return arguments.get(name)
    }

    @Override
    def <T> T getContext() {
        return context
    }

    @Override
    def <T> T getRoot() {
        return null
    }

    @Override
    GraphQLFieldDefinition getFieldDefinition() {
        return null
    }

    @Override
    List<Field> getFields() {
        return null
    }

    @Override
    GraphQLOutputType getFieldType() {
        return null
    }

    @Override
    ExecutionTypeInfo getFieldTypeInfo() {
        return null
    }

    @Override
    GraphQLType getParentType() {
        return null
    }

    @Override
    GraphQLSchema getGraphQLSchema() {
        return null
    }

    @Override
    Map<String, FragmentDefinition> getFragmentsByName() {
        return null
    }

    @Override
    ExecutionId getExecutionId() {
        return null
    }

    @Override
    DataFetchingFieldSelectionSet getSelectionSet() {
        return null
    }
}
