package madridgug.immutable

import java.time.LocalTime
import groovy.transform.Immutable

@Immutable
class Brand {
    String name
    LocalTime birthdate // <1>
}
