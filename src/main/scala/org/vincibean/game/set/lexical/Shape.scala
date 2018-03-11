package org.vincibean.game.set.lexical

import enumeratum._

import scala.collection.immutable

sealed trait Shape extends EnumEntry

object Shape extends Enum[Shape] {

  val values: immutable.IndexedSeq[Shape] = findValues

  case object Ovals extends Shape
  case object Squiggles extends Shape
  case object Diamonds extends Shape

}
