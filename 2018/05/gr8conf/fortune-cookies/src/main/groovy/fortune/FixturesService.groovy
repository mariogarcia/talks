package fortune

import javax.inject.Inject
import groovy.sql.Sql
import ratpack.service.Service
import ratpack.service.StartEvent

class FixturesService implements Service {

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

    sql.execute '''
    INSERT INTO cookies
      (AUTHOR, TEXT)
    VALUES
    ('Anonymous', 'Dont talk to strangers')
    '''

    sql.execute '''
    INSERT INTO cookies
      (AUTHOR, TEXT)
    VALUES
      ('Anonymous', 'The greatest risk is not taking one')
    '''
  }
}
