package org.vincibean.game.set.service

import org.vincibean.game.set.lexical.Player

trait PlayerService {

  def assignOnePointTo(p: Player): Player = p.copy(score = p.score.increase)

  def removeOnePointFrom(p: Player): Player = p.copy(score = p.score.decrease)

}
