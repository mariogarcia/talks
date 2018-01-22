import static ratpack.groovy.Groovy.ratpack
import static bond.common.SystemResources.classpath

ratpack {
  include classpath('handlers.groovy')
  include classpath('bindings.groovy')
}
