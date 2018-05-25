package fortune.security

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import fortune.security.instrumentation.Authentication
import fortune.security.instrumentation.AuthenticationService
import fortune.security.instrumentation.Authorization
import fortune.security.instrumentation.AuthorizationService

/**
 * @since 0.1.0
 */
class SecurityModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AuthenticationService).in(Scopes.SINGLETON)
        bind(Authentication).in(Scopes.SINGLETON)
        bind(AuthorizationService).in(Scopes.SINGLETON)
        bind(Authorization).in(Scopes.SINGLETON)
    }
}
