package greach.meta

class ToEverythingTest extends GroovyTestCase {

    void testCollectorWorksWithAsteroidLocal() {
        assertScript '''
        //tag::body[]
        package greach.meta

        @ToEverything
        class A {
            String name
        }

        A aInstance = new A()

        assert aInstance.toJson()
        assert aInstance.toString()
        // end::body[]
        '''
    }
}
