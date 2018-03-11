package org.vincibean.game.set.service

import org.vincibean.game.set.lexical.Score

trait ScoreService {

  def addPoint(point: Int)(s: Score): Score = s.copy(i = s.i + point)

  val addOnePoint: Score => Score = addPoint(1)
  val removeOnePoint: Score => Score = addPoint(-1)

}
