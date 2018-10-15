package madridgug.namedvariant

import java.time.LocalDateTime
import groovy.util.logging.Slf4j
import groovy.transform.*

@Slf4j
class AnotherCarProcessor {
    @NamedVariant
    void process(String user, @NamedParam LocalDateTime now,
        @NamedDelegate Car car) {
        log.info "$user ha procesado el modelo ${car.model} el $now"
    }
}
