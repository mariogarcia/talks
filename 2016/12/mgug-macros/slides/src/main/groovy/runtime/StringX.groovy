package runtime

class StringX {
    String word

    StringX(String word) {
        this.word = word
    }

    def methodMissing(String name, def args) {
        println "$name"

        return StringUtils."$name"(word)
    }
}
