package org.vincibean.game.set

import org.specs2.Specification
import org.specs2.specification.core.SpecStructure

class SeqOpsSpec extends Specification {
  override def is: SpecStructure =
    s2"""
        SeqOps must
          return None when an out of boud index is provided ${Seq(1).findAt(99) must beNone}
          return Some when an index in scope is provided ${Seq(1, 2).findAt(0) must beSome(
      1)}
      """
}
