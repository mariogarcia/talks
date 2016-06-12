package properties

import static SimpleProperty.SUM

import static spock.genesis.Gen.tuple
import static spock.genesis.Gen.integer

import spock.lang.Specification

class SumCheckCodeSpec extends Specification {

    //tag::commutative[]
    void 'check sum function'() {
        when: 'adding up x and y'
        Integer result = SUM(x, y)
        Integer reverse = SUM(y, x)

        then: 'it has to be z'
        result == z

        and: 'reverse has to be z'
        reverse == z

        where: 'possible values are'
        x    | y   |  z
        1    | 1   |  2
        0    | -1  | -1
        10   | -1  |  9
    }
    //end::commutative[]
}
