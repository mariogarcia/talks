package madridgug.immutable

import groovy.transform.Immutable

@Immutable
class Car {
    Brand brand // <1>
    String model
    Double hp
    Map<?, String> extra // <2>
}
