package properties

import static Functions.SUM

import static spock.genesis.Gen.tuple
import static spock.genesis.Gen.integer

import spock.lang.Specification

//tag::commutative[]
class SumCheckFirstSpec extends Specification {
    void 'check commutative property'() {
        when: 'two numbers are added,'
        then: 'the sum is the same'
        and:  'regardless of the order of the addends'
    }
}
//end::commutative[]
