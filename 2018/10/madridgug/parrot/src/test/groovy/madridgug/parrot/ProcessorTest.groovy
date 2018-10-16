package madridgug.parrot

import org.junit.Test

class FunctionsTest {

    @Test
    void 'check groovydocs on comments'() {
       // tag::from_comments[]
        String content = Processor
            .methods
            .find { it.name == 'process' }
            .groovydoc
            .content

        assert content == 'accesible1 // <1>'
        // end::from_comments[]
    }

    @Test
    void 'check groovydocs as annotation'() {
       // tag::from_annotation[]
        String content = Processor
            .methods
            .find { it.name == 'check' }
            .groovydoc
            .content

        assert content == 'accesible2'
       // end::from_annotation[]
    }
}
