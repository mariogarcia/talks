package greach

import io.cirill.relay.annotation.RelayType
import io.cirill.relay.annotation.RelayField

@RelayType(description='An optional description of a film')
class Film {

  @RelayField(description='An optional description of name')
  String title
  Integer year
  String bond
}
