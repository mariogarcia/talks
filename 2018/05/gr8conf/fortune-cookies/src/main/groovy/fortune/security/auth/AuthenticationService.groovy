package fortune.security.auth

import javax.inject.Inject

import com.auth0.jwt.interfaces.DecodedJWT
import fortune.security.SecurityAwareConfig
import fortune.security.UserProfile
import fortune.security.UserProfileRepository
import ratpack.exec.Blocking
import ratpack.exec.Promise
import graphql.GraphQLError
import graphql.schema.DataFetchingEnvironment
import gql.DSL
import ratpack.func.Action

/**
 * Service responsible to check application security
 *
 * @since 0.1.0
 */
class AuthenticationService {

    /**
     * @since 0.1.0
     */
    static final Promise<LoginResult> LOGIN_RESULT_ERROR = Promise.value(LoginResult.failure(DSL.error {
        message 'Invalid Credentials'
        extensions(i18n: 'ERROR.AUTH.INVALID')
    }))

    /**
     * @since 0.1.0
     */
    static class LoginResult {

        /**
         * @since 0.1.0
         */
        UserProfile userProfile

        /**
         * @since 0.1.0
         */
        GraphQLError error

        /**
         * @since 0.1.0
         */
        Boolean isError

        /**
         * @param userProfile
         * @return
         * @since 0.1.0
         */
        static LoginResult success(UserProfile userProfile) {
            return new LoginResult(userProfile: userProfile, isError: false)
        }

        /**
         * @param error
         * @return
         * @since 0.1.0
         */
        static LoginResult failure(GraphQLError error) {
            return new LoginResult(error: error, isError: true)
        }
    }

    /**
     * Accesses persisted security information
     *
     * @since 0.1.0
     */
    @Inject
    UserProfileRepository repository

    /**
     * Security related configuration
     *
     * @since 0.1.0
     */
    @Inject
    SecurityAwareConfig config

    /**
     * Returns basic information about a given user if the login
     * procedure has ended successfully
     *
     * @param env execution environment
     * @return a map with expected fields
     * @since 0.1.0
     */
    Promise<LoginResult> login(DataFetchingEnvironment env) {
        Map<String, String> credentials = env
                .arguments
                .credentials as Map<String, String>

        String password = JWT.hash(credentials.password)
        String username = credentials.username

        Promise<UserProfile> user = Blocking.get {
            repository.findByCredentials(username, password)
        }

        return user
            .map(this.&createPayload)
            .onError(buildLoginError()) as Promise<LoginResult>
    }

    /**
     * Retrieves a given user information by a given token
     *
     * @param token token to identify a given {@link UserProfile}
     * @return an {@link Optional} instance of a possible {@link UserProfile} instance
     * @since 0.1.0
     */
    Optional<UserProfile> checkToken(String token) {
        DecodedJWT decodedJWT = JWT.verifyToken(token, config.security.secret)

        return Optional
            .ofNullable(decodedJWT.token)
            .map(repository.&findByToken)
    }

    /**
     * Processes an exception and returns a {@link Promise} containing a {@link LoginResult}
     *
     * @return an {@link Action} which returns a {@link Promise} of type {@link LoginResult}
     * @since 0.1.0
     */
    static Action<? extends Throwable> buildLoginError() {
        return { Throwable th ->
            return LOGIN_RESULT_ERROR
        } as Action<? extends Throwable>
    }

    /*
     * Converts a {@link User} to the expected map for the login
     * response, including basic information such as the token
     *
     * @param user an instance of {@link User}
     * @return a map with basic login information
     * @since 0.1.0
     */
    private LoginResult createPayload(UserProfile user) {
        return LoginResult.success(user)
    }
}

