package fortune

import javax.inject.Inject
import javax.inject.Provider
import org.h2.jdbcx.JdbcDataSource
import javax.sql.DataSource

class DataSourceProvider implements Provider<DataSource> {

  @Inject
  Map config // <1>

  @Override
  DataSource get() { // <2>
    return new JdbcDataSource(
      URL: config.url,
      user: config.username,
      password: config.password ?: ''
    )
  }
}
