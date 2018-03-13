package org.vincibean.game.set.lexical

import cats.Order
import org.vincibean.game.set.service.PlayerService

object Player extends PlayerService {
  implicit class PlayerOps(val p: Player) extends AnyVal {
    def winOnePoint: Player = assignOnePointTo(p)
    def loseOnePoint: Player = removeOnePointFrom(p)
  }
  implicit def playerOrder(implicit ev: Order[Score]): Order[Player] =
    (x: Player, y: Player) => ev.compare(x.score, y.score)
}

final case class Player(name: String, score: Score = Score(0))
