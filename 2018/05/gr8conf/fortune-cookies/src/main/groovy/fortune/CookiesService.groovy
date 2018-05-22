package fortune

import javax.inject.Inject

import fortune.security.UserProfile
import groovy.sql.Sql
import graphql.schema.DataFetchingEnvironment
import groovy.util.logging.Slf4j
import ratpack.handling.Context
import ratpack.http.Request

@Slf4j
class CookiesService {

  @Inject
  Sql sql

  Map findRandomCookie(DataFetchingEnvironment env) {
    Context ctx = env.context as Context
    Request req = ctx.request

    log.info("reading a random cookie: ${req.maybeGet(UserProfile)}")

    return sql.firstRow("SELECT * FROM cookies ORDER BY ID DESC LIMIT 1")
  }
}
