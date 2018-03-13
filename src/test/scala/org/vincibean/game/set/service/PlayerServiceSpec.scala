package org.vincibean.game.set.service

import org.scalacheck.Prop
import org.specs2.matcher.MatchResult
import org.specs2.scalacheck.{ScalaCheckFunction1, ScalaCheckFunction2}
import org.specs2.specification.core.SpecStructure
import org.specs2.{ScalaCheck, Specification}
import org.vincibean.game.set.lexical.{Player, Score}

class PlayerServiceSpec
    extends Specification
    with ScalaCheck
    with PlayerService {
  override def is: SpecStructure =
    s2"""
          assignOnePoint must
            add 1 point to a player $p1
          removeOnePoint must
            remove 1 point to a player $p2
          findWinner must
            return the player with the highest score $p3
            return the first player if all have the same score $e4
      """

  def p1: ScalaCheckFunction1[Int, MatchResult[Int]] = prop { (i: Int) =>
    assignOnePointTo(Player("", Score(i))).score.i must beEqualTo(i + 1)
  }

  def p2: ScalaCheckFunction1[Int, MatchResult[Int]] = prop { (i: Int) =>
    removeOnePointFrom(Player("", Score(i))).score.i must beEqualTo(i - 1)
  }

  def p3: ScalaCheckFunction2[Int, Int, Prop] =
    prop { (s1: Int, s2: Int) =>
      (s1 > s2) ==> {
        val pl1 = Player("p1", Score(s1))
        val pl2 = Player("p2", Score(s2))
        findWinner(pl1, pl2) must beEqualTo(pl1)
      }
    }

  def e4: MatchResult[Player] = {
    val s = 42
    val pl1 = Player("p1", Score(s))
    val pl2 = Player("p2", Score(s))
    findWinner(pl1, pl2) must beEqualTo(pl2)
  }
}
