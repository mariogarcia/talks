package fortune.cookies

import java.util.concurrent.CompletableFuture

import graphql.schema.DataFetchingEnvironment

/**
 * Service to access fortune cookies stored in database
 *
 * @since 0.1.0
 */
interface CookiesService {
    /**
     * Gets a random cookie from database
     *
     * @param env GraphQL {@link DataFetchingEnvironment}
     * @return a {@link CompletableFuture} which yields a Map
     * @since 0.1.0
     */
    CompletableFuture<Map> findRandomCookie(DataFetchingEnvironment env)

    /**
     * Lists fortune cookies from database.
     *
     * @param env GraphQL {@link DataFetchingEnvironment}
     * @return a {@link CompletableFuture} which yields a list of cookies
     * @since 0.1.0
     */
    CompletableFuture<List<Map>> listCookies(DataFetchingEnvironment env)
    /**
     * Lists fortune cookies from database.
     *
     * @param env GraphQL {@link DataFetchingEnvironment}
     * @return a {@link CompletableFuture} which yields the created cookie
     * @since 0.1.0
     */
    CompletableFuture<Map> createCookie(DataFetchingEnvironment env)
}
