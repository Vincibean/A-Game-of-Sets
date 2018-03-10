package org.vincibean.game.set.service

import org.scalacheck.{Arbitrary, Gen, Prop}
import org.specs2.matcher.MatchResult
import org.specs2.scalacheck.{
  ScalaCheckFunction1,
  ScalaCheckFunction2,
  ScalaCheckFunction3
}
import org.specs2.specification.core.SpecStructure
import org.specs2.{ScalaCheck, Specification}
import org.vincibean.game.set.ArbitraryCards
import org.vincibean.game.set.lexical._

class DifferenceServiceSpec
    extends Specification
    with ScalaCheck
    with ArbitraryCards
    with DifferenceService {
  override def is: SpecStructure =
    s2"""
        different3 can
         pass the equality of primitives (Ints) ${equalityOf[Int]}
         fail with 'almost' equal primitives (Ints) ${almostEqualityOf[Int]}
         fail with different primitives (Ints) ${disequalityOf[Int]}
         pass the equality of instances (Strings) ${equalityOf[String]}
         fail with 'almost' equal instances (Strings) ${almostEqualityOf[String]}
         fail with different instances (Strings) ${disequalityOf[String]}
         pass the equality of objects (Colors) ${equalityOf[Color]}
         fail with 'almost' equal objects (Colors) ${almostEqualityOf[Color]}
         fail with different objects (Colors) ${disequalityOf[Color]}
         pass the equality of instances (Cards) ${equalityOf[Card]}
         fail with 'almost' equal instances (Cards) $e1
         fail with different instances (Cards) ${disequalityOf[Card]}
         fail with empty case classes ${equalityOf[Empty]}
      """

  def e1: MatchResult[Boolean] = {
    val c1 = Card(Ovals, Red, One, Solid)
    val c2 = Card(Ovals, Red, One, Solid)
    val c3 = Card(Ovals, Red, Two, Solid)
    totallyDifferent3(c1, c2, c3) should beFalse
  }

  def equalityOf[T](implicit ev: Arbitrary[T])
    : ScalaCheckFunction1[T, MatchResult[Boolean]] = {
    prop { (i: T) =>
      totallyDifferent3(i, i, i) must beFalse
    }
  }

  def almostEqualityOf[T](
      implicit ev: Arbitrary[T]): ScalaCheckFunction2[T, T, Prop] = {
    prop { (x: T, y: T) =>
      (x != y) ==> {
        totallyDifferent3(x, y, y) must beFalse
      }
    }
  }

  def disequalityOf[T](
      implicit ev: Arbitrary[T]): ScalaCheckFunction3[T, T, T, Prop] = {
    prop { (x: T, y: T, z: T) =>
      ((x != y) && (y != z) && (x != z)) ==> {
        totallyDifferent3(x, y, z) must beTrue
      }
    }
  }

  case class Empty()
  implicit val empties: Arbitrary[Empty] = Arbitrary(Gen.const(Empty()))

}
