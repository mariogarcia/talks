package fortune.security.auth

import javax.inject.Inject

import fortune.security.UserProfile
import graphql.execution.instrumentation.NoOpInstrumentation
import graphql.execution.instrumentation.parameters.InstrumentationFieldFetchParameters
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import groovy.transform.TupleConstructor
import groovy.util.logging.Slf4j
import ratpack.handling.Context
import ratpack.http.Request

/**
 * Naive authorization mechanism based on `graphql-java`
 * instrumentation behavior
 *
 * @since 0.1.0
 */
@Slf4j
@TupleConstructor
class AuthenticationInstrumentation extends NoOpInstrumentation {

    @Inject
    AuthenticationService securityService

    @Override
    DataFetcher<?> instrumentDataFetcher(DataFetcher<?> dataFetcher, InstrumentationFieldFetchParameters params) {
        log.info('instrumenting DataFetcher to check authentication')

        DataFetchingEnvironment environment = params.environment
        Context context = environment.context as Context

        DataFetcher fetcher = context
                .header('Authorization')
                .flatMap(AuthenticationInstrumentation.&extractToken)
                .flatMap(securityService.&checkToken)
                .map(AuthenticationInstrumentation.&addUserProfileToRequest.curry(context))
                .map { dataFetcher }
                .orElse(dataFetcher) as DataFetcher

        return fetcher
    }

    /**
     * Adds the resolved {@link UserProfile} to the current context {@link Request} object
     *
     * @param context the current {@link Context}
     * @param profile the resolved {@link UserProfile}
     * @return the current {@link Request} instance
     * @since 0.1.0
     */
    static Request addUserProfileToRequest(Context context, UserProfile profile) {
        log.debug('add user profile to request')
        return context.request.add(UserProfile, profile)
    }

    /**
     * Cleans up authorization information found in the authorization
     * header. It should only return the token part.
     *
     * @param authorization content found in the authorization header
     * @return an {@link Optional} containing the token found in the
     * authorization header
     * @since 0.1.0
     */
    static Optional<String> extractToken(String authorization) {
        log.debug('cleaning up JWT information')
        return Optional.ofNullable(authorization.trim() - 'JWT ')
    }
}
