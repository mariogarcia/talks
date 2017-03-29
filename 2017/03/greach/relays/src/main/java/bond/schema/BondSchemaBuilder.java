package bond.schema;

import static graphql.Scalars.GraphQLInt;
import static graphql.Scalars.GraphQLString;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;

import graphql.schema.GraphQLSchema;
import graphql.schema.GraphQLObjectType;

import bond.db.Queries;

public class BondSchemaBuilder {

  public GraphQLSchema buildSchema() {

    GraphQLObjectType filmType =
    // tag::filmType[]
      GraphQLObjectType.newObject()
      .name("Film")
      .field(newFieldDefinition()
             .type(GraphQLString)
             .name("title"))
      .field(newFieldDefinition()
             .type(GraphQLInt)
             .name("year"))
      .build();
    // end::filmType[]

    // tag::queryType[]
    GraphQLObjectType lastFilm =
      GraphQLObjectType.newObject()
      .name("Queries")
      .field(newFieldDefinition()
             .type(filmType)
             .name("lastFilm")
             .dataFetcher(Queries::findLastFilm))
      .build();

    return GraphQLSchema
      .newSchema()
      .query(lastFilm)
      .build();
    // end::queryType[]
  }
}
