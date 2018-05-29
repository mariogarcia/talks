package fortune.graphql

import javax.inject.Inject
import javax.inject.Provider

import fortune.security.instrumentation.Authorization
import graphql.execution.instrumentation.ChainedInstrumentation
import graphql.execution.instrumentation.Instrumentation

/**
 * Loads all instrumentations
 *
 * @since 0.1.0
 */
class InstrumentationProvider implements Provider<Instrumentation> {

    /**
     * Authorization instrumentation. Checks whether the current
     * user profile is allowed to access a given path
     *
     * @since 0.1.0
     */
    @Inject
    Authorization authorization

    @Override
    Instrumentation get() {
        List<Instrumentation> workflow = [
            authorization
        ]

        return new ChainedInstrumentation(workflow)
    }
}
