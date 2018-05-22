package fortune.graphql

import javax.inject.Inject
import javax.inject.Provider

import fortune.security.auth.AuthenticationInstrumentation
import graphql.execution.instrumentation.ChainedInstrumentation
import graphql.execution.instrumentation.Instrumentation

/**
 * @since 0.1.0
 */
class InstrumentationProvider implements Provider<Instrumentation> {

    /**
     * @since 0.1.0
     */
    @Inject
    AuthenticationInstrumentation authenticationInstrumentation

    @Override
    Instrumentation get() {
        return new ChainedInstrumentation([
            authenticationInstrumentation
        ])
    }
}
