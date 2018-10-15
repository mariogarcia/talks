package madridgug.namedvariant

import java.time.LocalDateTime
import spock.lang.Specification
import uk.org.lidalia.slf4jtest.TestLogger
import uk.org.lidalia.slf4jtest.TestLoggerFactory

class NamedVariantSpec extends Specification {

    void 'passing an instance of car'() {
        given: 'a test logger'
        TestLogger logger = TestLoggerFactory.getTestLogger(CarProcessor)

        and: 'a car and a processor instance'
        // tag::normal[]
        Car car = new Car(brand: 'ferrari', model: 'F450', hp: 450)
        CarProcessor processor = new CarProcessor()

        when: 'processing the car'
        processor.process(car)
        // end::normal[]

        then: 'the logger has to be invoked'
        logger
            .getLoggingEvents()
            .find()
            .message == 'processing: F450'

        cleanup: 'cleaning up the logger'
        logger.clear()
    }

    void 'passing the named variants of car'() {
        given: 'a test logger'
        TestLogger logger = TestLoggerFactory.getTestLogger(CarProcessor)

        // tag::by_params[]
        and: 'a processor instance'
        CarProcessor processor = new CarProcessor()

        when: 'processing the car'
        processor.process(brand: 'ferrari', model: 'F450', hp: 450)
        // end::by_params[]

        then: 'the logger has to be invoked'
        logger
            .getLoggingEvents()
            .find()
            .message == 'processing: F450'

        cleanup: 'cleaning up the logger'
        logger.clear()
    }

    void 'using @NamedParam'() {
        given: 'a test logger'
        TestLogger logger = TestLoggerFactory.getTestLogger(AnotherCarProcessor)

        // tag::with_named_params[]
        and: 'a processor instance'
        AnotherCarProcessor processor = new AnotherCarProcessor()

        when: 'processing the car'
        processor.process(
            'John Doe',
            now: LocalDateTime.now(),
            brand: 'ferrari',
            model: 'F450',
            hp: 450,
        )
        // end::with_named_params[]

        then: 'the logger has to be invoked'
        logger
            .getLoggingEvents()
            .find()
            .message ==~ 'John Doe ha procesado el modelo F450 el .*'

        cleanup: 'cleaning up the logger'
        logger.clear()
    }
}
