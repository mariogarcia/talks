package madridgug.autoimplement

import static java.time.LocalDateTime.now

import groovy.util.logging.Slf4j
import groovy.transform.AutoImplement

@Slf4j
@AutoImplement(code = {
    log.error("Necesito + tiempo ok? Prueba a las: ${now() + 120}")
})
class VeryNoisyMessageSender implements MessageSender {

}
