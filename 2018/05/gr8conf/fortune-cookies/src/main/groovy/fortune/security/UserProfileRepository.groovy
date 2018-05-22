package fortune.security

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

    /**
     * @param token
     * @return
     * @since 0.1.0
     */
    UserProfile findByToken(String token)
}
