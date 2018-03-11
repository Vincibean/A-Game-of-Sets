package org.vincibean.game.set.lexical

import enumeratum._

import scala.collection.immutable

sealed trait Shading extends EnumEntry

object Shading extends Enum[Shading] {

  val values: immutable.IndexedSeq[Shading] = findValues

  case object Solid extends Shading
  case object Striped extends Shading
  case object Outline extends Shading

}
