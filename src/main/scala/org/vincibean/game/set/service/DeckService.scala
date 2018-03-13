package org.vincibean.game.set.service

import eu.timepit.refined.auto._
import org.vincibean.game.set.lexical._
import org.vincibean.game.set.{Natural, PositiveNatural}

import scala.util.Random

trait DeckService extends DifferenceService {

  val singleDeck: Seq[Card] = for {
    shape <- Shape.values
    color <- Color.values
    number <- Number.values
    shading <- Shading.values
  } yield Card(shape, color, number, shading)

  def deckFrom(decksNum: Natural): Deck = {
    val decks =
      (0 until decksNum).map(_ => singleDeck).foldLeft(Seq.empty[Card])(_ ++ _)
    Deck(Random.shuffle(decks))
  }

  def layDownCards(deck: Deck, num: PositiveNatural): (Deck, Deck) = {
    val (laidDown, remaining) = deck.cards.splitAt(num)
    (Deck(laidDown), Deck(remaining))
  }

  def pickCards(
      deck: Deck)(c1: Natural, c2: Natural, c3: Natural): Option[Hand] =
    for {
      card1 <- deck.cards.findAt(c1)
      card2 <- deck.cards.findAt(c2)
      card3 <- deck.cards.findAt(c3)
      if totallyDifferent3(c1, c2, c3)
    } yield Hand(card1, card2, card3)

}
