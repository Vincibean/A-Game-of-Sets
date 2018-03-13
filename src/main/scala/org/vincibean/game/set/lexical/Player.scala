package org.vincibean.game.set.lexical

import cats.Order
import org.vincibean.game.set.service.PlayerService
import cats.syntax.order._

object Player extends PlayerService {

  implicit class PlayerOps(val p: Player) extends AnyVal {
    def winOnePoint: Player = assignOnePointTo(p)
    def loseOnePoint: Player = removeOnePointFrom(p)
  }

  implicit def playerOrder: Order[Player] =
    (x: Player, y: Player) => x.score.compare(y.score)
}

final case class Player(name: String, score: Score = Score(0))
