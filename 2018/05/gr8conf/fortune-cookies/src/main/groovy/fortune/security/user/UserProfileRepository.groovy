package fortune.security.user

import org.pac4j.core.profile.UserProfile

/**
 * @since 0.1.0
 */
interface UserProfileRepository {

    /**
     * @param username
     * @param password
     * @return
     * @since 0.1.0
     */
    UserProfile findByCredentials(String username, String password)
}
