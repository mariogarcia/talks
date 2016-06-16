package generators

import static Functions.SUM

import static spock.genesis.Gen.any
import static spock.genesis.Gen.tuple
import static spock.genesis.Gen.getInteger

import spock.genesis.generators.values.NullGenerator

import spock.lang.Ignore
import spock.lang.Specification

class SumCheckDataPipesSpec extends Specification {

    static final Integer LIMIT = 100

    //tag::commutative[]
    void 'check commutative property'() {
        when: 'two numbers are added,'
        then: 'the sum is the same'
        and:  'regardless of the order of the addends'
        SUM(x, y) == SUM(y, x)

        where: 'possible values are'
        x << (1..100)
        y << (100..1)
    }
    //end::commutative[]

    //tag::associative[]
    void 'check associative property'() {
        when: 'three or more numbers are added'
        then: 'the sum is the same'
        and: 'regardless of the grouping of the addends'
        SUM(x, SUM(y, z)) == SUM(SUM(x,y), z)

        where: 'sequence is'
        x << (1..100)

        and: 'y depends on x'
        y = x + 1

        and: 'the invariant is'
        z = 32
    }
    //end::associative[]

    // tag::distributive[]
    void 'check distributive property'() {
        expect: """
        The sum of two numbers times a third number is
        equal to the sum of each addend times the third number
        """
        SUM(x, y) * z == SUM(x * z, y * z)

        where: 'possible values are'
        [x, y, z] << (1..100).collect { n -> [n, n + 1, n + 2] }
    }
    // end::distributive[]
}
