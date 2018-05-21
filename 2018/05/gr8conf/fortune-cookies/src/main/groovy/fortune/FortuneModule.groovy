package fortune

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import graphql.schema.GraphQLSchema
import javax.sql.DataSource
import ratpack.service.Service

class FortuneModule extends AbstractModule {

  @Override
  void configure() {
    bind(GraphQLSchema) // <1>
      .toProvider(SchemaProvider)
      .in(Scopes.SINGLETON)

    bind(DataSource) // <2>
      .toProvider(DataSourceProvider)
      .in(Scopes.SINGLETON)

    bind(Service) // <3>
      .to(FixturesService)
      .in(Scopes.SINGLETON)

    bind(CookiesService) // <4>
      .in(Scopes.SINGLETON)
  }
}
