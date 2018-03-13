package org.vincibean.game.set.service

import cats.Order
import org.vincibean.game.set.lexical.Player
import cats.syntax.order._

trait PlayerService {

  def assignOnePointTo(p: Player): Player = p.copy(score = p.score.increase)

  def removeOnePointFrom(p: Player): Player = p.copy(score = p.score.decrease)

  def findWinner(p1: Player, p2: Player)(implicit ev: Order[Player]): Player =
    p1 max p2

}
