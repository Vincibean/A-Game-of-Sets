package org.vincibean.game.set.service

import cats.Eq
import cats.implicits._
import org.scalacheck.{Arbitrary, Prop}
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

class EqualityServiceSpec
    extends Specification
    with ScalaCheck
    with ArbitraryCards
    with EqualityService {
  override def is: SpecStructure =
    s2"""
        equal3 can
         test the equality of primitives (Ints) ${equalityOf[Int]}
         fail with 'almost' equal primitives (Ints) ${almostEqualityOf[Int]}
         fail with different primitives (Ints) ${disequalityOf[Int]}
         test the equality of instances (Strings) ${equalityOf[String]}
         fail with 'almost' equal instances (Strings) ${almostEqualityOf[String]}
         fail with different instances (Strings) ${disequalityOf[String]}
         test the equality of instances (Cards) ${equalityOf[Card]}
         fail with 'almost' equal instances (Cards) ${almostEqualityOf[Card]}
         fail with different instances (Cards) ${disequalityOf[Card]}
      """

  def equalityOf[T: Eq: Arbitrary]
    : ScalaCheckFunction1[T, MatchResult[Boolean]] = {
    prop { (i: T) =>
      equal3(i, i, i) must beTrue
    }
  }

  def almostEqualityOf[T: Eq: Arbitrary]: ScalaCheckFunction2[T, T, Prop] = {
    prop { (x: T, y: T) =>
      (x != y) ==> {
        equal3(x, y, y) must beFalse
      }
    }
  }

  def disequalityOf[T: Eq](
      implicit ev: Arbitrary[T]): ScalaCheckFunction3[T, T, T, Prop] = {
    prop { (x: T, y: T, z: T) =>
      ((x != y) && (y != z) && (x != z)) ==> {
        equal3(x, y, z) must beFalse
      }
    }
  }

}
