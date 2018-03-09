package org.vincibean.game.set.service

import org.specs2.Specification
import org.specs2.matcher.MatchResult
import org.specs2.specification.core.SpecStructure
import org.vincibean.game.set.lexical._

class EqualityServiceSpec extends Specification with EqualityService {
  override def is: SpecStructure =
    s2"""
        equal3 can
         test the equality of primitives (Ints) ${equal3(1, 1, 1) must beTrue}
         fail with different primitives (Ints) ${equal3(1, 2, 3) must beFalse}
         fail with 'almost' equal primitives (Ints) ${equal3(1, 1, 3) must beFalse}
         test the equality of instances (Strings) ${equal3("", "", "") must beTrue}
         fail with different instances (Strings) ${equal3("1", "2", "3") must beFalse}
         fail with 'almost' equal instances (Strings) ${equal3("", "", "1") must beFalse}
         test the equality of objects (Colors) ${equal3(Red, Red, Red) must beTrue}
         fail with different objects (Colors) ${equal3(Red, Green, Purple) must beFalse}
         fail with 'almost' equal objects (Colors) ${equal3(Red, Purple, Red) must beFalse}
         test the equality of instances (Cards) $e1
         fail with different instances (Cards) $e2
         fail with 'almost' equal instances (Cards) $e3
      """

  def e1: MatchResult[Boolean] = {
    val c1 = Card(Ovals, Red, One, Solid)
    val c2 = Card(Ovals, Red, One, Solid)
    val c3 = Card(Ovals, Red, One, Solid)
    equal3(c1, c2, c3) must beTrue
  }

  def e2: MatchResult[Boolean] = {
    val c1 = Card(Ovals, Red, One, Solid)
    val c2 = Card(Squiggles, Purple, Two, Outline)
    val c3 = Card(Diamonds, Green, Three, Striped)
    equal3(c1, c2, c3) must beFalse
  }

  def e3: MatchResult[Boolean] = {
    val c1 = Card(Ovals, Red, One, Solid)
    val c2 = Card(Squiggles, Purple, Two, Outline)
    val c3 = Card(Ovals, Red, One, Solid)
    equal3(c1, c2, c3) must beFalse
  }

}
