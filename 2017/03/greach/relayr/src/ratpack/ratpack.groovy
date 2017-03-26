import static ratpack.groovy.Groovy.ratpack
import static helthix.utils.SystemResources.classpath

/**
 *  _  _     _ _   _    _
 * | || |___| | |_| |_ (_)_ __
 * | __ / -_) |  _| ' \| \ \ /
 * |_||_\___|_|\__|_||_|_/_\_\
 *
 * This is the main configuration entry point for Helthix.
 */
ratpack {

  /**
   * HANDLERS AND BINDINGS
   * =====================
   *
   * Handlers and bindings should be configured in their respective
   * configuration files
   */
  include classpath('handlers.groovy')
  include classpath('bindings.groovy')
}
