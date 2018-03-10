package org.vincibean.game.set.service

import org.specs2.Specification
import org.specs2.matcher.MatchResult
import org.specs2.specification.core.SpecStructure
import org.vincibean.game.set.lexical._

class PlayServiceSpec extends Specification with PlayService {
  override def is: SpecStructure =
    s2"""
        isSet should
          return true when used on a hand of totally identical cards $e1
          return true when used on a hand of totally different cards $e2
          return false when used on a hand of 'almost' identical cards $e3
      """

  def e1: MatchResult[Boolean] = {
    val c1 = Card(Ovals, Red, One, Solid)
    isSet(Hand(c1, c1, c1)) should beTrue
  }

  def e2: MatchResult[Boolean] = {
    val c1 = Card(Ovals, Red, One, Solid)
    val c2 = Card(Squiggles, Purple, Two, Striped)
    val c3 = Card(Diamonds, Green, Three, Outline)
    isSet(Hand(c1, c2, c3)) should beTrue
  }

  def e3: MatchResult[Boolean] = {
    val c1 = Card(Ovals, Red, One, Solid)
    val c2 = Card(Ovals, Red, One, Solid)
    val c3 = Card(Diamonds, Green, Three, Outline)
    isSet(Hand(c1, c2, c3)) should beFalse
  }

}
