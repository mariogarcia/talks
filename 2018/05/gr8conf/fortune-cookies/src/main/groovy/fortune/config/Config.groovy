package fortune.config

import fortune.security.config.SecurityAwareConfig

/**
 * Bean containing app's configuration
 *
 * @since 0.1.0
 */
class Config implements SecurityAwareConfig {
    /**
     * Database connection properties
     *
     * @since 0.1.0
     */
    Database database

    /**
     * Security related properties
     *
     * @since 0.1.0
     */
    SecurityConfig security
}
