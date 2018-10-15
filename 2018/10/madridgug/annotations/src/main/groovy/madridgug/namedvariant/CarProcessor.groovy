package madridgug.namedvariant

import groovy.util.logging.Slf4j
import groovy.transform.NamedVariant
import groovy.transform.NamedDelegate

@Slf4j
class CarProcessor {
    @NamedVariant
    void process(@NamedDelegate Car car) {
        log.info "processing: ${car.model}"
    }
}
