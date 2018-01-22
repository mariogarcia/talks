package bond.handler

import com.google.inject.AbstractModule
import com.google.inject.Scopes

/**
 *
 */
class HandlerModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(Handler).in(Scopes.SINGLETON)
  }
}
