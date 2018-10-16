package madridgug.parrot

import org.junit.Test

class FunctionsTest {

    @Test
    void 'check only lambdas'() {
        assert Functions.applyLambdas() == 3
    }

    @Test
    void 'check applyLambdas2'() {
        assert Functions.applyLambdas2() == 6
    }

    @Test
    void 'check apply both'() {
        assert Functions.applyBoth() == 3
    }

    @Test
    void 'check methor references'() {
        assert Functions.applyMethodReferences(2) == 5
    }
}
