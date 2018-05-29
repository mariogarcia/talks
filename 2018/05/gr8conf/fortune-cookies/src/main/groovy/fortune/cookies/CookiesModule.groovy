package fortune.cookies

import com.google.inject.AbstractModule
import com.google.inject.Scopes

class CookiesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CookiesServiceImpl).in(Scopes.SINGLETON)
        bind(CookiesRepository).to(SqlCookiesRepository).in(Scopes.SINGLETON)
    }
}
