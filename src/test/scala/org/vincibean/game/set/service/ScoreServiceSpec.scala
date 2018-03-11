package org.vincibean.game.set.service

import org.specs2.matcher.MatchResult
import org.specs2.scalacheck.ScalaCheckFunction1
import org.specs2.specification.core.SpecStructure
import org.specs2.{ScalaCheck, Specification}
import org.vincibean.game.set.lexical.Score

class ScoreServiceSpec extends Specification with ScalaCheck with ScoreService {
  override def is: SpecStructure =
    s2"""
          addOnePoint must
            add 1 point to whichever score $p1
          removeOnePoint must
            remove 1 point to whichever score $p2
      """

  def p1: ScalaCheckFunction1[Int, MatchResult[Int]] = prop { (i: Int) =>
    addOnePoint(Score(i)).i must beEqualTo(i + 1)
  }

  def p2: ScalaCheckFunction1[Int, MatchResult[Int]] = prop { (i: Int) =>
    removeOnePoint(Score(i)).i must beEqualTo(i - 1)
  }
}
