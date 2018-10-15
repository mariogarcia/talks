package madridgug.immutable

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import com.google.inject.Provides

class GuiceModule extends AbstractModule {
    @Override
    protected void configure() {
        // tag::guice_di[]
        def ctor = SecurityConfig2.getConstructor(URI, String)

        bind(SecurityConfig2)
            .toConstructor(ctor)
            .in(Scopes.SINGLETON)
        // end::guice_di[]
    }

    @Provides
    String getKey() {
        return "key"
    }

    @Provides
    URI getProvider() {
        URI.create 'https://nowhereprovider.io'
    }
}
