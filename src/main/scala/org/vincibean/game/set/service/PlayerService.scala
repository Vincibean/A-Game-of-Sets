package org.vincibean.game.set.service

import cats.syntax.order._
import org.vincibean.game.set.lexical.Player

trait PlayerService {

  def assignOnePointTo(p: Player): Player = p.copy(score = p.score.increase)

  def removeOnePointFrom(p: Player): Player = p.copy(score = p.score.decrease)

  def findWinner(p1: Player, p2: Player, ps: Player*): Player =
    ps.foldLeft(p1 max p2)(_ max _)

}
