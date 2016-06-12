package properties

import static SimpleProperty.SUM

import static spock.genesis.Gen.tuple
import static spock.genesis.Gen.integer

import spock.lang.Specification

class SumCheckPropertiesSpec extends Specification {

    //tag::commutative[]
    void 'check commutative property'() {
        when: 'two numbers are added,'
        then: 'the sum is the same'
        and:  'regardless of the order of the addends'
        SUM(x, y) == SUM(y, x)

        where: 'possible values are'
        x    | y
        1    | 1
        0    | -1
        10   | -1
    }
    //end::commutative[]

    // tag::associative[]
    void 'check associative property'() {
        when: 'three or more numbers are added'
        then: 'the sum is the same regardless of the grouping of the addends'
        SUM(x, SUM(y, z)) == SUM(SUM(x,y), z)

        where: 'possible values are'
           x   |   y   |   z
           1   |   1   |   1
          10   |  -1   |   0
          -1   |   0   |   3
    }
    // end::associative[]

    void 'check additive identity property'() {
        expect: 'the sum of any number and zero is the original number.'
        SUM(x, 0) == x

        where: 'possible values are'
        x << [1,0,10,-1,20,1000,-1000]
    }

    void 'check distributive property'() {
        expect: """
        The sum of two numbers times a third number is
        equal to the sum of each addend times the third number
        """
        SUM(x, y) * z == SUM(x * z, y * z)

        where: 'possible values are'
           x   |   y   |   z
           1   |   1   |   1
          10   |  -1   |   0
          -1   |   0   |   3
    }
}
