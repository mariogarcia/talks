package madridgug.autofinal

import spock.lang.Specification

class AutoFinalSpec extends Specification {

    void 'check reverse'() {
        given: 'an instance of word processor'
        WordProcessor processor = new WordProcessor()

        when: 'trying to reverse word'
        String reverse = processor.reverseWord(new Word(word: 'amigo'))

        then: 'the reverse version should be provided'
        reverse == 'ogima'
    }

    void 'check cumply size because not using @Autofinal'() {
        given: 'an instance of word processor'
        WordProcessor processor = new WordProcessor()

        when: 'trying to get size'
        Integer size = processor.size(new Word(word: 'amigo'))

        then: 'it gives us a WRONG size'
        size != 5
    }
}
