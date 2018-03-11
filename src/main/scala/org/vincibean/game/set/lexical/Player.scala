package org.vincibean.game.set.lexical

import org.vincibean.game.set.service.PlayerService

object Player extends PlayerService {
  implicit class PlayerOps(val p: Player) extends AnyVal {
    def winOnePoint: Player = assignOnePoint(p)
    def loseOnePoint: Player = removeOnePoint(p)
  }
}

final case class Player(name: String, score: Score = Score(0))
