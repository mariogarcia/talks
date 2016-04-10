package greach.local

class WithLoggingExplainedTest {


    void testWithLoggingExplained() {
        assertScript '''
        // tag::testWithLoggingExplained[]
        package greach.local

        class A {
            @WithLogging
            void doSomething() {
                // println "Starting doSomething"

                println "mystuff"

                // println "Ending doSomething"
            }
        }
        // end::testWithLoggingExplained[]
        '''
    }

}
