package org.vincibean.game.set.lexical

import enumeratum._

import scala.collection.immutable

sealed trait Number extends EnumEntry

object Number extends Enum[Number] {

  val values: immutable.IndexedSeq[Number] = findValues

  case object One extends Number
  case object Two extends Number
  case object Three extends Number

}
