package feedback

import static feedback.ValidationService.Person
import static feedback.ValidationService.SUM
import static feedback.ValidationService.BadSUM

import spock.genesis.Gen
import spock.genesis.generators.Generator

import spock.lang.Ignore
import spock.lang.Unroll
import spock.lang.Specification

class ValidationServiceSpec extends Specification {

    @Ignore
    //tag::powerassert[]
    void 'check different implementations'() {
        expect: "same values"
        SUM(x, y) == BadSUM(x,y)

        where: "values"
        [x, y] << Gen.tuple(Gen.integer, Gen.integer).take(100)
    }
    //end::powerassert[]

    // tag::validperson[]
    void 'check type validation better'() {
    // end::validperson[]
        given: "an instance of validation service"
        ValidationService service = new ValidationService()

        when: "validating a valid person"
        def errors = service.validatePerson(person)

        then: "results in no errors"
        errors.isEmpty()

        where: "generated people are"
        person << validPersonGenerator.take(100)
    }

    Generator getValidPersonGenerator() {
        return Gen.type(Person,
                        age: Gen.integer(0, 120),
                        name: Gen.string(51,100),
                        address: Gen.string)
    }

    @Ignore
    // tag::unroll[]
    @Unroll("Check person [AGE: #person.age| NAME:#person.name | ADDR: #person.address]")
    void 'check type validation better extended'() {
    // end::unroll[]
        given: "an instance of validation service"
        ValidationService service = new ValidationService()

        when: "validating a valid person"
        def errors = service.validatePerson(person)

        then: "results in no errors"
        errors.isEmpty()

        where: "generated people are"
        person << invalidPersonGenerator.take(100)
    }

    Generator getInvalidPersonGenerator() {
        return Gen.type(Person,
                        age: Gen.integer(0, 120),
                        name: Gen.string(0, 51),
                        address: Gen.string(0, 10))
    }
}
