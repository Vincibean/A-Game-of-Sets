package org.vincibean.game.set.lexical

import cats.Order
import org.vincibean.game.set.service.ScoreService

object Score extends ScoreService {
  implicit class ScoreOps(s: Score) {
    def increase: Score = addOnePoint(s)
    def decrease: Score = removeOnePoint(s)
  }

  implicit def scoreOrder: Order[Score] =
    (x: Score, y: Score) => x.i.compare(y.i)
}

final case class Score(i: Int) extends AnyVal
