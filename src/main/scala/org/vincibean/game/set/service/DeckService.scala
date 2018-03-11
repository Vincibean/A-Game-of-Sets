package org.vincibean.game.set.service

import org.vincibean.game.set.lexical._

import scala.util.Random

trait DeckService {

  val singleDeck: Seq[Card] = for {
    shape <- Shape.values
    color <- Color.values
    number <- Number.values
    shading <- Shading.values
  } yield Card(shape, color, number, shading)

  // TODO shuffle
  def deckFrom(decksNum: Int): Deck = {
    val decks =
      (0 until decksNum).map(_ => singleDeck).foldLeft(Seq.empty[Card])(_ ++ _)
    Deck(Random.shuffle(decks))
  }

  def layDownCards(deck: Deck, num: Int): (Deck, Deck) = {
    val (laidDown, remaining) = deck.cards.splitAt(num)
    (Deck(laidDown), Deck(remaining))
  }

  def pickCards(deck: Deck)(c1: Int, c2: Int, c3: Int): Hand =
    Hand(deck.cards(c1), deck.cards(c2), deck.cards(c3))

}
