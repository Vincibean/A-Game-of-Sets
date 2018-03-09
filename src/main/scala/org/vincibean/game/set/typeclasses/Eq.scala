package org.vincibean.game.set.typeclasses

object Eq {
  implicit def eq[T]: Eq[T] = (x: T, y: T) => x.equals(y)
}

trait Eq[A] {

  def eq(x: A, y: A): Boolean

}
