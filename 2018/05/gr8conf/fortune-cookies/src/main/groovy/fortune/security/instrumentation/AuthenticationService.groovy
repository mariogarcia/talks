package fortune.security.instrumentation

import javax.inject.Inject
import groovy.util.logging.Slf4j

import com.auth0.jwt.interfaces.DecodedJWT
import fortune.security.config.SecurityAwareConfig
import fortune.security.user.UserProfile
import fortune.security.user.UserProfileRepository
import graphql.schema.DataFetchingEnvironment

/**
 * Service responsible to check application security
 *
 * @since 0.1.0
 */
@Slf4j
class AuthenticationService {

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
    UserProfile login(DataFetchingEnvironment env) {
        Map<String, String> credentials = env
                .arguments
                .credentials as Map<String, String>

        String password = JWT.hash(credentials.password)
        String username = credentials.username

        return repository.findByCredentials(username, password)
    }

    /**
     * Retrieves a given user information by a given token
     *
     * @param token token to identify a given {@link UserProfile}
     * @return an {@link Optional} instance of a possible {@link UserProfile} instance
     * @since 0.1.0
     */
    Optional<UserProfile> checkToken(String token) {
        DecodedJWT decodedJWT = JWT.verifyToken(token, "${config.security.crypto.secret}")

        return Optional
            .ofNullable(decodedJWT.token)
            .map(repository.&findByToken) as Optional<UserProfile>
    }
}

