package madridgug.macros.method

import spock.lang.Specification

class ProcessorWithStatisticsSpec extends Specification {

    // tag::statistics[]
    void 'check statistics'() {
        given: 'an instance of processor'
        ProcessorWithStatistics processor = new ProcessorWithStatistics()

        expect: 'number of methods'
        processor.methodCount == 3

        and: 'number of fields'
        processor.fieldCount == 1
    }
    // end::statistics[]
}
