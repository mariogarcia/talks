package gql

import spock.lang.Specification

import graphql.GraphQLError
import graphql.schema.GraphQLSchema
import graphql.schema.GraphQLObjectType
import graphql.validation.ValidationError

import gql.test.util.Queries

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

  void 'execute query with static value'() {
    when: 'building the type'
    GraphQLSchema schema = DSL.schema {
      queries {
        type('helloQuery') {
          description'simple droid'
          fields {
            field('hello') {
              description'name of the droid'
              type GraphQLString
              staticValue 'world'
            }
          }
        }
      }
    }

    and: 'executing a query against that schema'
    Map<String,Map> dataMap = DSL
      .execute(schema, '{ hello }')
      .data

    then: 'we should get the expected name'
    dataMap.hello == 'world'
  }

  void 'execute query with fetcher'() {
    when: 'building the type'
    GraphQLObjectType filmType = DSL.type('film') {
      fields {
        field('title') {
          description 'title of the film'
          type GraphQLString
        }
      }
    }

    and: 'building the schema'
    GraphQLSchema schema = DSL.schema {
      queries {
        type('lastBondFilm') {
          description'get last Bond film'
          fields {
            field('lastFilm') {
              description'last film'
              type filmType
              fetcher Queries.&findLastFilm
            }
          }
        }
      }
    }

    and: 'executing a query against that schema'
    Map<String,Map> dataMap = DSL
      .execute(schema, query)
      .data

    then: 'we should get the expected name'
    dataMap.lastFilm.title == 'SPECTRE'

    where: 'executed query is'
    query = '''
      {
        lastFilm {
          title
        }
      }
    '''
  }

  void 'validate mandatory field'() {
    when: 'building the type'
    GraphQLObjectType filmType = DSL.type('film') {
      fields {
        field('title') {
          description 'title of the film'
          nonNullType GraphQLString
        }
      }
    }

    and: 'building the schema'
    GraphQLSchema schema = DSL.schema {
      queries {
        type('lastBondFilm') {
          description'get last Bond film'
          fields {
            field('lastFilm') {
              description'last film'
              type filmType
              fetcher Queries.&findLastFilm
            }
          }
        }
      }
    }

    and: 'executing a query against that schema'
    List<GraphQLError> errors = DSL
      .execute(schema, query)
      .errors

    then: 'we should get the expected name'
    errors.find() instanceof ValidationError

    where: 'executed query is'
    query = '''
      {
        lastFilm
      }
    '''
  }
}
