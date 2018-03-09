package org.vincibean.game.set.service

import org.vincibean.game.set.typeclasses.Eq

trait EqualityService {

  def equal3[T](t1: T, t2: T, t3: T)(implicit ev: Eq[T]): Boolean =
    ev.eq(t1, t2) && ev.eq(t2, t3)

}
