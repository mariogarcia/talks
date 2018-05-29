package fortune.security

import com.google.inject.AbstractModule
import com.google.inject.Scopes

import fortune.security.instrumentation.Authorization
import fortune.security.instrumentation.AuthorizationService
import fortune.security.pac4j.Authenticator

/**
 * @since 0.1.0
 */
class SecurityModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AuthorizationService).in(Scopes.SINGLETON)
        bind(Authorization).in(Scopes.SINGLETON)
        bind(Authenticator).in(Scopes.SINGLETON)
    }
}
