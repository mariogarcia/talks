package greach.builder

import asteroid.A
import asteroid.local.Apply
import asteroid.local.Local

@Apply(A.TO.FIELD)
@Local(ToMD5Impl)
@interface ToMD5 { }
