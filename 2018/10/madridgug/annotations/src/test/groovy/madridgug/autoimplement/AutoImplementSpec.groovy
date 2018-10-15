package madridgug.autoimplement

import madridgug.exception.NotEnoughTimePalException
import spock.lang.Specification
import uk.org.lidalia.slf4jtest.TestLogger
import uk.org.lidalia.slf4jtest.TestLoggerFactory

class AutoImplementSpec extends Specification {

    void 'check default values'() {
        given: 'a default instance'
        SurrealMessageSender sender = new SurrealMessageSender()

        when: 'sending a message'
        UUID id = sender.sendMessage("message")

        then: 'you\'ve got null'
        !id

        when: 'asking for a boolean (primitive)'
        boolean isReceived = sender.isMessageReceived(UUID.randomUUID())

        then: 'it should be false'
        isReceived == false

        when: 'asking for a Boolean (object)'
        Boolean isSent = sender.isMessageSent(UUID.randomUUID())

        then: 'it should be null'
        !isSent
    }

    void 'check partial implementation'() {
        given: 'a default instance'
        PartialMessageSender sender = new PartialMessageSender()

        and: 'a mocked logger implementation'
        TestLogger logger = TestLoggerFactory.getTestLogger(PartialMessageSender)

        when: 'sending a message'
        UUID id = sender.sendMessage('message')

        then: 'we got the id'
        id

        and: 'the log has been called'
        logger.getAllLoggingEvents().size() == 1

        and: 'calling any other method returns default values'
        !sender.isMessageReceived()
        !sender.isMessageSent()

        cleanup: 'cleaning up logger'
        logger.clear()
    }

    void 'check noisy implementation'() {
        given: 'a default instance'
        NoisyMessageSender sender = new NoisyMessageSender()

        and: 'a mocked logger implementation'
        TestLogger logger = TestLoggerFactory.getTestLogger(NoisyMessageSender)

        when: 'sending a message'
        UUID id = sender.sendMessage('message')

        then: 'we should get the id'
        id

        and: 'the log has been called'
        logger.getLoggingEvents().size() == 1

        when: 'calling any other method'
        sender.isMessageReceived()

        then: 'a friendly exception should be thrown'
        thrown(NotEnoughTimePalException)
    }

    void 'check very noisy implementation'() {
        given: 'a default instance'
        VeryNoisyMessageSender sender = new VeryNoisyMessageSender()

        and: 'a mocked logger implementation'
        TestLogger logger = TestLoggerFactory.getTestLogger(VeryNoisyMessageSender)

        when: 'sending a message'
        def id = sender.sendMessage('message')
        def loggerEvents = logger.getLoggingEvents()

        then: ''
        !id
        loggerEvents.size() == 1
        loggerEvents.first().level.toString() == 'ERROR'
    }
}
