package org.vincibean.game.set.service

import cats.Eq
import cats.syntax.eq._

trait EqualityService {

  def equal3[T](t1: T, t2: T, t3: T)(implicit ev: Eq[T]): Boolean =
    t1 === t2 && t2 === t3

}
