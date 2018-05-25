package fortune

import fortune.security.user.DefaultUserProfile
import fortune.security.user.UserProfile
import fortune.security.user.UserProfileRepository

/**
 * @since 0.1.0
 */
class UserRepository implements UserProfileRepository {

    @Override
    UserProfile findByCredentials(String username, String password) {
        return new DefaultUserProfile(
            name: 'john',
            token: 'token',
            roles: ['USER']
        )
    }

    @Override
    UserProfile findByToken(String token) {
        return new DefaultUserProfile(
            name: 'john',
            token: 'token',
            roles: ['USER']
        )
    }
}