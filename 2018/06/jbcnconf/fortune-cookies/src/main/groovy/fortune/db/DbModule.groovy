package fortune.db

import javax.sql.DataSource

import com.google.inject.AbstractModule
import com.google.inject.Scopes

class DbModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DataSource).toProvider(DataSourceProvider).in(Scopes.SINGLETON)
    }
}
