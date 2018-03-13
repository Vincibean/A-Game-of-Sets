package org.vincibean.game.set.lexical

import cats.Eq

object Card {
  implicit val cardEq: Eq[Card] = (x: Card, y: Card) => x == y
}

final case class Card(shape: Shape,
                      color: Color,
                      number: Number,
                      shading: Shading)
