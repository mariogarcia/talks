package generators

import static Functions.SUM

import static spock.genesis.Gen.any
import static spock.genesis.Gen.tuple
import static spock.genesis.Gen.getInteger

import spock.genesis.generators.values.NullGenerator

import spock.lang.Ignore
import spock.lang.Specification

class SumCheckGenerativeSpec extends Specification {

    static final Integer LIMIT = 100

    //tag::commutative[]
    void 'check commutative property'() {
        when: 'two numbers are added,'
        then: 'the sum is the same'
        and:  'regardless of the order of the addends'
        SUM(x, y) == SUM(y, x)

        where: 'possible values are'
        [x, y] << tupleOfTwo.take(LIMIT)
    }
    //end::commutative[]

    void 'check associative property'() {
        when: 'three or more numbers are added'
        then: 'the sum is the same regardless of the grouping of the addends'
        SUM(x, SUM(y, z)) == SUM(SUM(x,y), z)

        where: 'possible values are'
        [x, y, z] << tupleOfThree.take(LIMIT)
    }

    void 'check additive identity property'() {
        expect: 'the sum of any number and zero is the original number.'
        SUM(x, 0) == x

        where: 'possible values are'
        x << getInteger().take(LIMIT)
    }

    void 'check distributive property'() {
        expect: """
        The sum of two numbers times a third number is
        equal to the sum of each addend times the third number
        """
        SUM(x, y) * z == SUM(x * z, y * z)

        where: 'possible values are'
        [x, y] << tupleOfTwo.take(LIMIT)

        z << any(-10..10).take(LIMIT)
    }

    def getTupleOfTwo() {
        return tuple(getInteger(), getInteger())
    }

    def getTupleOfThree() {
        return tuple(getInteger(), getInteger(), new NullGenerator())
    }
}
