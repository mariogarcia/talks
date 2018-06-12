package fortune.security

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import fortune.config.Config
import fortune.security.config.SecurityAwareConfig
import fortune.security.instrumentation.Authorization
import fortune.security.instrumentation.AuthorizationService
import fortune.security.pac4j.Authenticator
import fortune.security.user.UserProfileRepository
import fortune.security.user.UserProfileRepositoryImpl

/**
 * @since 0.1.0
 */
class SecurityModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AuthorizationService).in(Scopes.SINGLETON)
        bind(Authorization).in(Scopes.SINGLETON)
        bind(Authenticator).in(Scopes.SINGLETON)
        bind(UserProfileRepository).to(UserProfileRepositoryImpl).in(Scopes.SINGLETON)
        bind(SecurityAwareConfig).to(Config).in(Scopes.SINGLETON)
    }
}
