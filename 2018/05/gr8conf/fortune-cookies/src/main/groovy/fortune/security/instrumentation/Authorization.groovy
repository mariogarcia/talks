package fortune.security.instrumentation

import javax.inject.Inject
import java.util.function.Predicate
import groovy.util.logging.Slf4j

import fortune.security.user.UserProfile
import gql.DSL
import graphql.execution.instrumentation.NoOpInstrumentation
import graphql.execution.instrumentation.parameters.InstrumentationFieldFetchParameters
import graphql.GraphQLError
import graphql.schema.DataFetcher
import graphql.schema.DataFetchingEnvironment
import ratpack.handling.Context

/**
 * Checks the permissions of the current user, and allows or rejects the current execution
 *
 * @since 0.1.0
 */
@Slf4j
class Authorization extends NoOpInstrumentation {

    /**
     * Service responsible to check whether a {@link UserProfile} is allowed to
     * visit a given path
     *
     * @since 0.1.0
     */
    @Inject
    AuthorizationService authorizationService

    @Override
    DataFetcher<?> instrumentDataFetcher(DataFetcher<?> dataFetcher, InstrumentationFieldFetchParameters params) {
        log.debug('instrumenting DataFetcher to check authorization')

        DataFetchingEnvironment environment = params.environment
        Context context = environment.context as Context
        String executionPath = params.typeInfo.path

        return context
            .request
            .maybeGet(UserProfile)
            .filter(byUserProfile(executionPath))
            .map { dataFetcher }
            .orElse(getAuthorizationFailureFetcher(params)) as DataFetcher
    }

    private Predicate<UserProfile> byUserProfile(String path) {
        return { UserProfile profile ->
            return authorizationService.isAllowed(profile, path)
        }
    }

    /**
     * Returns a {@link DataFetcher} which raises a security exception
     *
     * @params parameters of the current execution
     * @return a {@link DataFetcher} raising an {@link GraphQLError}
     * @since 0.1.0
     */
    static DataFetcher getAuthorizationFailureFetcher(InstrumentationFieldFetchParameters params) {
        return DSL.errorFetcher(params) {
            message 'Forbidden Resource'
            extensions(i18n: 'ERROR.SECURITY.FORBIDDEN')
        }
    }
}
