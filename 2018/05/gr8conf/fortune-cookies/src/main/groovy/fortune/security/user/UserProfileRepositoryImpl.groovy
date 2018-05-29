package fortune.security.user

import org.pac4j.core.profile.UserProfile

/**
 * @since 0.1.0
 */
class UserRepository implements UserProfileRepository {

    @Override
    UserProfile findByCredentials(String username, String password) {
        UserProfile profile = new UserProfile()
        profile.roles = ['USER']

        return profile
    }
}