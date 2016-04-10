package greach.builder

class EvenCheckerTest extends GroovyTestCase {

    void testEvenChecker() {
        assertScript '''
        package greach.builder

        // tag::asttestimport[]
        import groovy.transform.ASTTest
        // end::asttestimport[]
        import org.codehaus.groovy.ast.ClassHelper

        // tag::asttestbody[]
        @ASTTest({
            assert node
                .properties
                .every { it.type == ClassHelper.make(Integer) }
        })
        @EvenChecker
        class A {
            Integer max
            Integer min

            String toString() {
                return "A"
            }
        }
        // end::asttestbody[]

        A a = new A(max: 10, min: 5)

        assert a.checkEven(a.max)
        assert !a.checkEven(a.min)
        '''
    }
}
