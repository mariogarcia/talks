package helthix.types

import spock.lang.Specification

import graphql.schema.GraphQLSchema
import graphql.schema.GraphQLObjectType

class TraineeSpec extends Specification {

  void 'checking GraphQL schema'() {
    when: 'getting type GraphQL information'
    GraphQLObjectType personType = Trainee.getGraphQLDefinition()

    and: 'generating a new schema with this info'
    GraphQLSchema schema = GraphQLSchema.newSchema()
      .query(personType)
      .build()

    then: 'we should get a proper output'
    schema
  }
}
