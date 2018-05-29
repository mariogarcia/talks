package fortune.cookies

import javax.inject.Inject
import java.util.concurrent.CompletableFuture
import groovy.sql.Sql
import groovy.util.logging.Slf4j

import gql.ratpack.exec.Futures
import graphql.schema.DataFetchingEnvironment
import org.pac4j.core.profile.UserProfile
import ratpack.handling.Context

/**
 * Service to access fortune cookies stored in database
 *
 * @since 0.1.0
 */
@Slf4j
class CookiesService {

  /**
   * Database connection ref
   *
   * @since 0.1.0
   */
  @Inject
  Sql sql

  /**
   * Gets a random cookie from database
   *
   * @param env GraphQL {@link DataFetchingEnvironment}
   * @return a {@link CompletableFuture} which yields a Map
   * @since 0.1.0
   */
  CompletableFuture<Map> findRandomCookie(DataFetchingEnvironment env) {
      return Futures.blocking({
          sql.firstRow('SELECT count(1) as count FROM cookies').get('count')
      }).thenApply({ Long count ->
          new Random().nextInt(count.toInteger())
      }).thenApply({ Integer random ->
          return sql.firstRow("SELECT * FROM cookies OFFSET :offset", offset: random)
      })
  }

  /**
   * Lists fortune cookies from database.
   *
   * @param env GraphQL {@link DataFetchingEnvironment}
   * @return a {@link CompletableFuture} which yields a list of cookies
   * @since 0.1.0
   */
  CompletableFuture<List<Map>> listCookies(DataFetchingEnvironment env) {
      // Gathering query parameters from GraphQL environment
      Selectors.ListCookiesParams params = Selectors.listCookiesParams(env)

      // Example on how to access UserProfile and using it
      // in our service's methods
      UserProfile profile = env
          .context
          .asType(Context)
          .get(UserProfile)

      // Executing a blocking action
      return Futures.blocking({
          log.info("user ${profile.getAttribute('username')} is listing cookies")

          sql.rows('SELECT * from cookies', params.offset, params.max)
      })
  }
}

