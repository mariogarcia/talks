package fortune.config

import fortune.security.SecurityAwareConfig

/**
 * @since 0.1.0
 */
class Config implements SecurityAwareConfig {
    /**
     * @since 0.1.0
     */
    Database database

    /**
     * @since 0.1.0
     */
    SecurityConfig security
}
