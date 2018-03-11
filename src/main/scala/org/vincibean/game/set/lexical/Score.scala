package org.vincibean.game.set.lexical

import org.vincibean.game.set.service.ScoreService

object Score extends ScoreService {
  implicit class ScoreOps(s: Score) {
    def increase: Score = addOnePoint(s)
    def decrease: Score = removeOnePoint(s)
  }
}

final case class Score(i: Int) extends AnyVal
