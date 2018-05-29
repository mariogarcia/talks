package fortune.security.config

/**
 * This interface should be implemented by the configuration
 * bean to make all security properties available through the
 * application
 *
 * @since 0.1.0
 */
interface SecurityAwareConfig {

    /**
     * Contains information about hashing passwords (crypto)
     * and authorization mappings (mappings)
     *
     * @since 0.1.0
     */
    static class SecurityConfig {

        /**
         * Contains authorization mappings information
         *
         * @return authorization mapping properties
         * @since 0.1.0
         */
        AuthorizationConfig authorization
    }

    /**
     * Authorization properties
     *
     * @since 0.1.0
     */
    static class AuthorizationConfig {
        /**
         * Authorization mappings
         * <code>
         *     mappings:
         *       - Queries/randomCookie: USER,COOKIES
         * <code>
         *
         * @since 0.1.0
         */
        Map<String, String> mappings

        Boolean schema

        Boolean allowPartials
    }

    /**
     * Returns all security related properties
     *
     * @since 0.1.0
     */
    SecurityConfig getSecurity()
}
