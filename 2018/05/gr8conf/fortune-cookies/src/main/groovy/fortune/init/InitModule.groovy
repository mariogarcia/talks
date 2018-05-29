package fortune.init

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import ratpack.service.Service

class InitModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Service).to(FixturesService).in(Scopes.SINGLETON)
    }
}
