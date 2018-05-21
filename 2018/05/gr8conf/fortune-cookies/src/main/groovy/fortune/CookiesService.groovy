package fortune

import javax.inject.Inject
import groovy.sql.Sql
import graphql.schema.DataFetchingEnvironment

class CookiesService {

  @Inject
  Sql sql

  Map findRandomCookie(DataFetchingEnvironment env) {
    sql.firstRow "SELECT * FROM cookies ORDER BY ID DESC LIMIT 1"
  }
}
