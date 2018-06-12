package fortune.init

import javax.inject.Inject
import groovy.sql.Sql
import ratpack.service.Service
import ratpack.service.StartEvent

/**
 * Loads initial database data
 *
 * @since 0.1.0
 */
class FixturesService implements Service {

  /**
   * Database connection ref
   *
   * @since 0.1.0
   */
  @Inject
  Sql sql

  @Override
  void onStart(StartEvent e) {
    sql.execute '''
    CREATE TABLE cookies (
      ID INT PRIMARY KEY AUTO_INCREMENT,
      AUTHOR VARCHAR(255),
      TEXT VARCHAR(500)
    )
    '''

    sql.execute "INSERT INTO cookies (AUTHOR, TEXT) VALUES ('Anonymous', 'Dont talk to strangers')"
    sql.execute "INSERT INTO cookies (AUTHOR, TEXT) VALUES ('Anonymous', 'The greatest risk is not taking one')"
    sql.execute "INSERT INTO cookies (AUTHOR, TEXT) VALUES ('Anonymous', 'Groovy is so cool!')"
    sql.execute "INSERT INTO cookies (AUTHOR, TEXT) VALUES ('Anonymous', 'Better late than never')"

  }
}
