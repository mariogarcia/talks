import groovy.transform.CompileStatic

/**
 * This compiler configuration adds static compilation to ALL Groovy
 * classes
 *
 * @since 0.1.0
 */
withConfig(configuration) {
  ast(CompileStatic)
}
