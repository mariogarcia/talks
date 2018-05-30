package fortune.cookies

import com.google.inject.AbstractModule
import com.google.inject.Scopes

/**
 * This module creates instances for types related to cookies
 *
 * @since 0.1.0
 */
class CookiesModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CookiesServiceImpl).in(Scopes.SINGLETON)
        bind(CookiesRepository).to(SqlCookiesRepository).in(Scopes.SINGLETON)
    }
}
