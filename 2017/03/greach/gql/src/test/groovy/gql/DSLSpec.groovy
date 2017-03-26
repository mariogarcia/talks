package gql

import spock.lang.Specification

import graphql.schema.GraphQLObjectType

/**
 * @since 0.1.0
 */
class DSLSpec extends Specification {

  void 'build a simple type with a name'() {
    when: 'building the type'
    GraphQLObjectType type = DSL.type('Droid') {
      // nothing
    }

    then: 'the type should have the expected name'
    type.name == 'Droid'
  }

  void 'build a simple type with a name inside dsl'() {
    when: 'building the type'
    GraphQLObjectType type = DSL.type('Droid') {
      name 'DroidX'
    }

    then: 'the type should have the expected name'
    type.name == 'DroidX'
  }

  void 'build a simple type with a name and description'() {
    when: 'building the type'
    GraphQLObjectType type = DSL.type('Droid') {
      description'a simple droid'
    }

    then: 'the type should have the expected name'
    type.name == 'Droid'
    type.description == 'a simple droid'
  }

  void 'build a simple type with one field'() {
    when: 'building the type'
    GraphQLObjectType type = DSL.type('Droid') {
      description'simple droid'
      fields {
        field('name') {
          description'name of the droid'
          type GraphQLString
        }
      }
    }

    then: 'the type should have the expected features'
    type.name == 'Droid'
    type.description == 'simple droid'
    type.fieldDefinitions.size() == 1
    type.fieldDefinitions.first().name == 'name'
    type.fieldDefinitions.first().description == 'name of the droid'
  }

  void 'build a simple type with more than one field'() {
    when: 'building the type'
    GraphQLObjectType type = DSL.type('Droid') {
      description'simple droid'
      fields {
        field('name') {
          description'name of the droid'
          type GraphQLString
        }
        field('type') {
          description'type of the droid'
          type GraphQLString
        }
        field('age') {
          description 'age of the droid'
          type GraphQLInt
        }
      }
    }

    then: 'the type should have the name and desc'
    type.name == 'Droid'
    type.description == 'simple droid'

    and: 'there should be two fields'
    type.fieldDefinitions.size() == 3

    and: 'we should expect some field names'
    type.fieldDefinitions*.name == ['name', 'type', 'age']

    and: 'some descriptions'
    type.fieldDefinitions*.description == [
      'name of the droid',
      'type of the droid',
      'age of the droid',
    ]
  }
}
