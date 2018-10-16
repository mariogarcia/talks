package madridgug.parrot

class Elvis {

    // tag::new_elvis[]
    void newElvis(Integer x) {
        x ?= 0 // instead of x ? x : 0

        println x + 1
    }
    // end::new_elvis[]

    // tag::safe_indexing[]
    void safeIndexing() {
        def numbers = [1, 2, 3]

        println numbers?[10] // will print 'null'
    }
    // end::safe_indexing[]
}
