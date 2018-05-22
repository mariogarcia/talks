package fortune

import fortune.security.UserProfile
import fortune.security.UserProfileRepository

/**
 * @since 0.1.0
 */
class UserRepository implements UserProfileRepository {

    static class DefaultUserProfile implements UserProfile {
        String name
        String token
        List<String> roles
    }

    @Override
    UserProfile findByCredentials(String username, String password) {
        return [
            name: 'john',
            token: 'token',
            roles: ['ADMIN']
        ] as UserProfile
    }

    @Override
    UserProfile findByToken(String token) {
        return [
            name: 'john',
            token: 'token',
            roles: ['ADMIN']
        ] as UserProfile
    }
}