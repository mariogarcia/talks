package genesis

import static genesis.ValidationService.Person

import spock.genesis.Gen
import spock.genesis.generators.Generator
import spock.lang.Specification

class ValidationServiceSpec extends Specification {

    // tag::validate[]
    void 'check type validation'() {
        expect: """
        to be valid if not null and it is one of
        the expected values"""
        okIfNotNullAndIn(word)    ||
        koIfNotNullAndNotIn(word) ||
        koIfNull(word)

        where: "possible words are"
        word << (1..100).collect { n ->
            n % 2 == 0 ? null : (n <= 50 ? "someword" : "one")
        }
    }
    // end::validate[]

    // tag::validate2[]
    void 'check type validation'() {
        expect: """
        to be valid if not null and it is one of
        the expected values"""
        okIfNotNullAndIn(word)    ||
        koIfNotNullAndNotIn(word) ||
        koIfNull(word)

        where: "possible words are"
        word << Gen.any(null, Gen.string.find(), "one").take(100)
    }
    // end::validate2[]

    boolean okIfNotNullAndIn(String word) {
        return word in ["one","two"] && new ValidationService().validateString(word).isEmpty()
    }

    boolean koIfNotNullAndNotIn(String word) {
        return word || !word && !new ValidationService().validateString(word).isEmpty()
    }

    boolean koIfNull(String word) {
        !word && !(new ValidationService().validateString(word).isEmpty())
    }

    // tag::validateType[]
    void 'check type validation better'() {
        given: "an instance of validation service"
        ValidationService service = new ValidationService()

        when: "validating a valid person instance"
        def errors = service.validatePerson(person)

        then: "there will be no errors"
        errors.isEmpty()

        where: "generated people are"
        person << validPersonGenerator.take(100)
    }
    // end::validateType[]

    // tag::validateMap[]
    void 'check map validation'() {
        expect: """
        No errors are produced when using a valid
        set of map orders
        """
        ValidationService
            .validateMap(order)
            .isEmpty()

        where: "generated orders are"
        order << validOrders.take(100)
    }
    // end::validateMap[]

    // tag::mapGenerator[]
    Generator getValidOrders() {
        return Gen.map(id: Gen.long,
                       description: Gen.string)
    }
    // end::mapGenerator[]

    //tag::okGenerator[]
    Generator getValidPersonGenerator() {
        return Gen.type(Person,
                        age: Gen.integer(0, 120),
                        name: Gen.string,
                        address: Gen.string.then(Gen.once(null)))
    }
    //end::okGenerator[]
}
