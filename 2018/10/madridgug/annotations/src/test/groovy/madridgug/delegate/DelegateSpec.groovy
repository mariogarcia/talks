package madridgug.delegate

import spock.lang.Specification

class DelegateSpec extends Specification {

    void 'check which tennis player is greater'() {
        // tag::delegate[]
        given: 'two players'
        def good = new TennisPlayer(name: 'John', wins: [
            [name: 'Roland Garros 2014'],
            [name: 'Roland Garros 2015'],
            [name: 'Roland Garros 2016']
        ])

        def bad = new TennisPlayer(name: 'Peter', wins: [
            [name: 'Somewhere 2002']
        ])

        and: 'the good one should be greater than the bad one'
        good.intValue() > bad.intValue()
        // end::delegate[]
    }
}
