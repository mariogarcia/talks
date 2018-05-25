package fortune.security.user

/**
 * Default implementation of a {@link UserProfile}
 *
 * @since 0.1.0
 */
class DefaultUserProfile implements UserProfile {

    /**
     * Name of the user
     *
     * @since 0.1.0
     */
    String name

    /**
     * The token can be used for authentication
     *
     * @since 0.1.0
     */
    String token

    /**
     * Roles associated with this user
     *
     * @since 0.1.0
     */
    Set<String> roles

    /**
     * Builds a {@link DefaultUserProfile} instance representing
     * an anonymous user
     *
     * @since 0.1.0
     */
    static UserProfile ANONYMOUS() {
        return new DefaultUserProfile(name: 'ANONYMOUS', token: '', roles: ['ANONYMOUS'])
    }
}