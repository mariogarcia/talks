package madridgug.autofinal

import groovy.transform.AutoFinal

class WordProcessor {

    @AutoFinal
    String reverseWord(Word word) {
        // word = new Word(word: 'another') <1>
        return word.reverse()
    }

    Integer size(Word word) {
        word = new Word(word: 'another') // <2>
        return word.size()
    }
}
