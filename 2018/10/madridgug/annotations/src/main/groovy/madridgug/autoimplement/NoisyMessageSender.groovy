package madridgug.autoimplement

import madridgug.exception.NotEnoughTimePalException
import groovy.util.logging.Slf4j
import groovy.transform.AutoImplement

@Slf4j
@AutoImplement(exception = NotEnoughTimePalException)
class NoisyMessageSender implements MessageSender {

    @Override
    UUID sendMessage(String message) {
        log.info "message: $message"

        return UUID.randomUUID()
    }
}
