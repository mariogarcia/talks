package fortune.security

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import fortune.security.auth.AuthenticationInstrumentation
import fortune.security.auth.AuthenticationService

/**
 * @since 0.1.0
 */
class SecurityModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(AuthenticationService).in(Scopes.SINGLETON)
        bind(AuthenticationInstrumentation).in(Scopes.SINGLETON)
    }
}
