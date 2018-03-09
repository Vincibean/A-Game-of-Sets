package org.vincibean.game.set.typeclasses

import shapeless._
import shapeless.ops.hlist.{Mapper, ToTraversable, Zip}
import shapeless.syntax.std.tuple._

object Neq {

  private object mix extends Poly1 {
    implicit def caseTuple[T] = at[(T, T)] {
      case (t1, t2) => t1 != t2
    }
  }

  implicit def productNeq[C <: Product, H <: HList, Z <: HList, M <: HList](
      implicit gen: Generic.Aux[C, H],
      zipper: Zip.Aux[H :: H :: HNil, Z],
      mapper: Mapper.Aux[mix.type, Z, M],
      toTraversableAux: ToTraversable.Aux[M, scala.List, Boolean]): Neq[C] =
    (x: C, y: C) => {
      val hs1 = Generic[C].to(x)
      val hs2 = Generic[C].to(y)
      (hs1 zip hs2).map(mix).toList.reduce(_ && _)
    }

  def anyNeq[T]: Neq[T] = (x: T, y: T) => x != y

}

trait Neq[-A] {

  def neq(x: A, y: A): Boolean

}
