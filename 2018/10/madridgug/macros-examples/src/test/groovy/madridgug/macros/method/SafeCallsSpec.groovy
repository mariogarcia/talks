package madridgug.macros.method

import spock.lang.Specification

class SafeCallsSpec extends Specification {

    // tag::unsafe_calls[]
    void 'check unsafe calls'() {
        expect: 'unsafe calls to return ok'
        SafeCalls.inc(null) == 1
    }
    // end::unsafe_calls[]

    void 'check safe calls'() {
        expect: 'safe calls to return ok'
        SafeCalls.inc(x) == y

        where: 'possible valid values are'
        x << [1, 2, 3]
        y << [2, 3, 4]
    }
}
