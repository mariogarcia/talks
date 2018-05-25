package fortune

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import fortune.config.Config
import fortune.db.DataSourceProvider
import fortune.graphql.InstrumentationProvider
import fortune.graphql.SchemaProvider
import fortune.init.FixturesService
import fortune.security.config.SecurityAwareConfig
import fortune.security.user.UserProfileRepository
import graphql.execution.instrumentation.Instrumentation
import graphql.schema.GraphQLSchema
import javax.sql.DataSource
import ratpack.service.Service

class FortuneModule extends AbstractModule {

  @Override
  void configure() {
    bind(GraphQLSchema)
      .toProvider(SchemaProvider)
      .in(Scopes.SINGLETON)

    bind(DataSource)
      .toProvider(DataSourceProvider)
      .in(Scopes.SINGLETON)

    bind(Service)
      .to(FixturesService)
      .in(Scopes.SINGLETON)

    bind(CookiesService)
      .in(Scopes.SINGLETON)

    bind(UserProfileRepository)
      .to(UserRepository)
      .in(Scopes.SINGLETON)

    bind(Instrumentation)
    .toProvider(InstrumentationProvider)
    .in(Scopes.SINGLETON)

    bind(SecurityAwareConfig)
    .to(Config)
    .in(Scopes.SINGLETON)

  }
}
