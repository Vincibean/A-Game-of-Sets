package org.vincibean.game.set.service

import org.specs2.matcher.MatchResult
import org.specs2.scalacheck.ScalaCheckFunction1
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
      """

  def p1: ScalaCheckFunction1[Int, MatchResult[Int]] = prop { (i: Int) =>
    assignOnePoint(Player("", Score(i))).score.i must beEqualTo(i + 1)
  }

  def p2: ScalaCheckFunction1[Int, MatchResult[Int]] = prop { (i: Int) =>
    removeOnePoint(Player("", Score(i))).score.i must beEqualTo(i - 1)
  }
}
