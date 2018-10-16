package madridgug.parrot

import groovy.transform.Immutable

class Identity {
    @Immutable
    class Person {
        String name
    }
    void checkIdentity(Person left, Person right) {
        if (left === right) { // <1>
            log.info "es la misma persona"
        }
        if (left == right) { // <2>
            log.info "son personas con caracteristicas iguales"
        }
    }
}
