package org.vincibean.game.set.service

import org.vincibean.game.set.lexical._

trait HandService extends EqualityService with DifferenceService {

  def isSet(x: Hand): Boolean = {
    val Hand(c1, c2, c3) = x
    val p1 = equal3(c1, c2, c3)
    val p2 = totallyDifferent3(c1, c2, c3)
    p1 || p2
  }

}
