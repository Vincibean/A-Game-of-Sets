package org.vincibean.game

import eu.timepit.refined.api.Refined
import eu.timepit.refined.numeric._

package object set {

  type Natural = Int Refined NonNegative
  type PositiveNatural = Int Refined Positive

  implicit class SeqOps[A](val as: Seq[A]) extends AnyVal {
    def findAt(i: Int): Option[A] = as.lift(i)
  }

}
