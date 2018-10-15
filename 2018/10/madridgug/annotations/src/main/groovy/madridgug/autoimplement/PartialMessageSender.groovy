package madridgug.autoimplement

import groovy.util.logging.Slf4j
import groovy.transform.AutoImplement

@Slf4j
@AutoImplement
class PartialMessageSender implements MessageSender {

    @Override
    UUID sendMessage(String message) {
        log.info "message: $message"

        return UUID.randomUUID()
    }
}
