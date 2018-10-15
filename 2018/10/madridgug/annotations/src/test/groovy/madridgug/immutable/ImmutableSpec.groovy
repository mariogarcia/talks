package madridgug.immutable

import spock.lang.Specification
import com.google.inject.Injector
import com.google.inject.Guice

class ImmutableSpec extends Specification {

    void 'check dependency injection friendliness'() {
        // tag::di_friendly[]
        given: 'a Google Guice Injector'
        Injector di = Guice.createInjector(new GuiceModule())

        when: 'getting the configuration instance'
        SecurityConfig2 config = di.getInstance(SecurityConfig2)

        then: 'properties should\'ve been populated properly'
        config.key == 'key'
        config.provider == URI.create('https://nowhereprovider.io')

        when: 'trying to change properties again'
        config.key = 'another key'

        then: 'an error will be thrown'
        thrown(ReadOnlyPropertyException)
        // end::di_friendly[]
    }
}
