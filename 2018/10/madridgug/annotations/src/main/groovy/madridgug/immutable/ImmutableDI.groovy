package madridgug.immutable

import groovy.transform.*
import groovy.transform.options.*

@ImmutableBase
@TupleConstructor(defaults = false)
@PropertyOptions(propertyHandler = ImmutablePropertyHandler)
@AnnotationCollector
@interface ImmutableDI {

}
