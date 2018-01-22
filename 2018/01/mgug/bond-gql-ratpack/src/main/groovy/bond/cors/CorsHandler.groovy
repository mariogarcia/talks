package bond.cors

import ratpack.handling.Handler
import ratpack.handling.Context

/**
 * Allows Cors to make it easier to test front in dev
 *
 * @since 0.1.0
 */
class CorsHandler implements Handler {

  @Override
  void handle(Context ctx) {
    ctx
      .response
      .headers
      .add('Access-Control-Allow-Origin', '*')
      .add('Access-Control-Allow-Headers', 'Content-Type')

    ctx.next()
  }
}
