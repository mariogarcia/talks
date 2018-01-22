package bond.system

import com.google.inject.AbstractModule
import com.google.inject.Scopes

/**
 * Binds all required system services
 *
 * @since 0.1.0
 */
class SystemModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(SystemService).in(Scopes.SINGLETON)
    bind(SystemGraphQL).in(Scopes.SINGLETON)
  }
}
