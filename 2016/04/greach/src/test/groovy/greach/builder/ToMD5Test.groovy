// tag::classdeclaration[]
package greach.builder

class ToMD5Test extends GroovyTestCase {
// end::classdeclaration[]

    // tag::assertscript[]
    void testAddingToMD5() {
        assertScript '''
        // tag::abstractions[]
        package greach.builder

        class Order {
            @ToMD5 String name
            @ToMD5 String description
        }

        Order order = new Order(name: "john", description: "desc")

        assert order.nameToMD5()        == "527bd5b5d689e2c32ae974c6229ff785"
        assert order.descriptionToMD5() == "1dee80c7d5ab2c1c90aa8d2f7dd47256"
        // end::abstractions[]
        '''
    }
   // end::assertscript[]

    // tag::shouldFail[]
    void testFailsToUseAnInteger() {
        shouldFail '''
        package greach.builder

        class Order {
            @ToMD5 Integer month
        }
        '''
    }
    // end::shouldFail[]
}
