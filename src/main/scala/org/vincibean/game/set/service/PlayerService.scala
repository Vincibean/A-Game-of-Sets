package org.vincibean.game.set.service

import org.vincibean.game.set.lexical.Player

trait PlayerService {

  def assignOnePoint(p: Player): Player = p.copy(score = p.score.increase)

  def removeOnePoint(p: Player): Player = p.copy(score = p.score.decrease)

}
