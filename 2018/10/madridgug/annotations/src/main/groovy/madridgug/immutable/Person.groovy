package madridgug.immutable

import groovy.transform.Immutable

@Immutable
class Person {
    String name
    Integer age

    Optional<String> mobile
}
