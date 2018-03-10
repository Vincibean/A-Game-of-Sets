package org.vincibean.game.set

import org.scalacheck.{Arbitrary, Gen}
import org.vincibean.game.set.lexical._

trait ArbitraryCards {

  implicit val shapes: Arbitrary[Shape] = Arbitrary(
    Gen.oneOf(Ovals, Diamonds, Squiggles))

  implicit val colors: Arbitrary[Color] = Arbitrary(
    Gen.oneOf(Red, Purple, Green))

  implicit val numbers: Arbitrary[Number] = Arbitrary(
    Gen.oneOf(One, Two, Three))

  implicit val shadings: Arbitrary[Shading] = Arbitrary(
    Gen.oneOf(Solid, Striped, Outline))

  implicit val cards: Arbitrary[Card] = Arbitrary {
    for {
      s <- Arbitrary.arbitrary[Shape]
      c <- Arbitrary.arbitrary[Color]
      n <- Arbitrary.arbitrary[Number]
      sd <- Arbitrary.arbitrary[Shading]
    } yield Card(s, c, n, sd)
  }

}
