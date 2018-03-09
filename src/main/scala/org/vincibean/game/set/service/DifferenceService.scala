package org.vincibean.game.set.service

import org.vincibean.game.set.typeclasses.Neq

trait DifferenceService {

  def totallyDifferent3[T](t1: T, t2: T, t3: T)(
      implicit ev: Neq[T] = Neq.anyNeq): Boolean =
    ev.neq(t1, t2) && ev.neq(t2, t3) && ev.neq(t1, t3)

}
