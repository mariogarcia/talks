package fortune.cookies

import javax.inject.Inject
import java.util.concurrent.CompletableFuture
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
class CookiesServiceImpl implements  CookiesService {

  /**
   * Database connection ref
   *
   * @since 0.1.0
   */
  @Inject
  CookiesRepository repository

  @Override
  CompletableFuture<Map> findRandomCookie(DataFetchingEnvironment env) {
      return Futures
          .blocking(repository.&count)
          .thenApply(CookiesServiceImpl.&pickRandomNumberBetweenZeroAnd)
          .thenApply(repository.&getCookieAt)
  }

  private static Integer pickRandomNumberBetweenZeroAnd(Long upperBound) {
      return new Random().nextInt(upperBound.toInteger())
  }

  @Override
  CompletableFuture<List<Map>> listCookies(DataFetchingEnvironment env) {
      Selectors.ListCookiesParams params = Selectors.listCookiesParams(env)

      return Futures.blocking {
          repository.list(params)
      }
  }

  @Override
  CompletableFuture<Map> createCookie(DataFetchingEnvironment env) {
      Selectors.CreateCookieParams params = Selectors.createCookieParams(env)

      // Getting pac4j UserProfile from Context
      Context context = env.context as Context
      UserProfile profile = context.get(UserProfile)

      log.info("user ${profile.getAttribute('username')} is going to create a new cookie")

      return Futures.blocking {
          repository.create(params)
      }
  }
}

