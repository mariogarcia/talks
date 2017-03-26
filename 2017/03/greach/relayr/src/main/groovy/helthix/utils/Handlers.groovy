package helthix.utils

import ratpack.func.Action
import ratpack.handling.Handler
import ratpack.handling.Context
import ratpack.registry.Registry

/**
 * @since 0.1.0
 */
class Handlers {

  /**
   * Creates an instance of {@link Handler} binding the json payload
   * coming from request, creates an instance of the type passed as
   * parameter and passes that object to the next handler in the
   * chain.
   *
   * @param clazz the type of class you want an instance from
   * @return an instance of {@link BindingHandler}
   * @since 0.1.0
   */
  static Handler createBindingHandler(final Class clazz) {
    return { Context ctx -> ctx.parse(clazz).then(addToNext(ctx)) } as Handler
  }

  private static <T> Action<? super T> addToNext(final Context ctx) {
    return { T action -> ctx.next(Registry.single(action)) } as Action<? super T>
  }
}
