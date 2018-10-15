package madridgug.immutable

import groovy.transform.Canonical
import groovy.transform.ImmutableBase
import groovy.transform.PropertyOptions
import groovy.transform.options.ImmutablePropertyHandler

@ImmutableBase
@Canonical(defaults = false)
@PropertyOptions(propertyHandler = ImmutablePropertyHandler)
class SecurityConfig {
    URI provider
    String key
}
