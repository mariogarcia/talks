package fortune.db

import javax.inject.Inject
import javax.inject.Provider

import fortune.config.Config
import org.h2.jdbcx.JdbcDataSource
import javax.sql.DataSource

/**
 * Configures datasource connection
 *
 * @since 0.1.0
 */
class DataSourceProvider implements Provider<DataSource> {

  @Inject
  Config config

  @Override
  DataSource get() {
    return new JdbcDataSource(
      URL: config.database.url,
      user: config.database.username,
      password: config.database.password ?: ''
    )
  }
}
