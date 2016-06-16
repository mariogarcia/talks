package genesis

import static helios.Helios.validate
import static helios.Validators.positive
import static helios.Validators.matches
import static helios.Validators.inRangeOfString

//tag::import[]
import spock.genesis.Gen
//end::import[]

import spock.lang.Specification

class SimpleTypesSpec extends Specification {

    //tag::simpleinteger[]
    void 'check positive numbers'() {
        when: "Using a non positive integer"
        def intErrors = validate("positive", number, positive())

        then: "there will be errors"
        !intErrors.isEmpty()

        where:
        number << Gen.integer(-100,0).take(100)
    }
    //end::simpleinteger[]

    //tag::simplestring[]
    void 'check strings'() {
        when: "Using a shorter string"
        def stringErrors = validate("pattern",
                                    word,
                                    inRangeOfString(10, 16))

        then: "there will be errors"
        !stringErrors.isEmpty()

        where: 'possible wrong values are'
        word << Gen.string(1, 5).take(100)
    }
    //end::simplestring[]

    //tag::stringpattern[]
    void 'check strings'() {
        when: "Using an non matching string"
        def stringErrors = validate("pattern", word, matches("jbcn.*"))

        then: "there will be errors"
        !stringErrors.isEmpty()

        where: 'possible wrong values are'
        word << Gen.string(~'lond.*').take(100)
    }
    //end::stringpattern[]
}
