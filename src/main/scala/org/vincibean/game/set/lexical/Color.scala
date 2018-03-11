package org.vincibean.game.set.lexical

import enumeratum._

import scala.collection.immutable

sealed trait Color extends EnumEntry

object Color extends Enum[Color] {

  val values: immutable.IndexedSeq[Color] = findValues

  case object Red extends Color
  case object Purple extends Color
  case object Green extends Color

}
