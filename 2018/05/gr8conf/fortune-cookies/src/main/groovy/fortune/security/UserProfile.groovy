package fortune.security

/**
 * @since 0.1.0
 */
interface UserProfile {

    /**
     * @return
     * @since 0.1.0
     */
    String getName()

    /**
     * @return
     * @since 0.1.0
     */
    String getToken()

    /**
     * @return
     * @since 0.1.0
     */
    List<String> getRoles()
}
