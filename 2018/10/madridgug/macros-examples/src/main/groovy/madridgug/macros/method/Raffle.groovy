package madridgug.macros.method

import groovy.transform.Immutable

@Immutable
class Raffle {
    @ToMD5
    String title
    String prize
}
