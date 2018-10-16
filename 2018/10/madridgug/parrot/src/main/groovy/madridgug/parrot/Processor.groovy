package madridgug.parrot

class Processor {

    /**
     * @Groovydoc accesible1 // <1>
     */
    void process(String word) {
        println "process: $word"
    }

    @Groovydoc('accesible2') // <2>
    void check(String word) {
        println "check: $word"
    }
}
