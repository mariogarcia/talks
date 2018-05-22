package fortune.security

/**
 * @since 0.1.0
 */
interface SecurityAwareConfig {

    /**
     * @since 0.1.0
     */
    static class SecurityConfig {
        String secret
    }

    /**
     * @return
     * @since 0.1.0
     */
    SecurityConfig getSecurity()
}
