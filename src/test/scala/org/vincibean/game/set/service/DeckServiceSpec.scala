package org.vincibean.game.set.service

import org.scalacheck.{Arbitrary, Prop}
import org.specs2.matcher.MatchResult
import org.specs2.scalacheck.{
  ScalaCheckFunction1,
  ScalaCheckFunction2,
  ScalaCheckFunction3
}
import org.specs2.specification.core.SpecStructure
import org.specs2.{ScalaCheck, Specification}
import org.vincibean.game.set.ArbitraryCards
import org.vincibean.game.set.lexical._

class DeckServiceSpec
    extends Specification
    with ScalaCheck
    with DeckService
    with ArbitraryCards {
  override def is: SpecStructure =
    s2"""
        deckFrom must
          return an empty deck if a number less than or equal to 0 is given $p1
          return a full (with the appropriate number of cards) deck if a number greaten than 0 is given $e2
        layDownCards must
          use the right cardinality for laid down cards as well as remaining cards in the Deck $e3
        pickCards must
          return the expected cards $p4
      """

  def p1: ScalaCheckFunction1[Int, Prop] = prop { (i: Int) =>
    (i <= 0) ==> {
      deckFrom(i).cards must beEmpty
    }
  }

  // TODO
  def p2: ScalaCheckFunction1[Int, Prop] = prop { (i: Int) =>
    (i > 0) ==> {
      deckFrom(i).cards must haveSize(i * 81)
    }
  }

  def e2: MatchResult[Seq[Card]] = deckFrom(1).cards must haveSize(81)

  implicit val arbitraryDeck: Arbitrary[Deck] = Arbitrary {
    for {
      i <- Arbitrary.arbitrary[Int]
      if i > 0
    } yield deckFrom(i)
  }

  // TODO
  def p3: ScalaCheckFunction2[Int, Deck, Prop] = prop { (i: Int, d: Deck) =>
    ((i > 0) && (i <= d.cards.length)) ==> {
      val (laidDown, remaining) = layDownCards(Deck(singleDeck), i)
      (laidDown.cards must haveSize(i)) and (remaining.cards must haveSize(
        singleDeck.length - i))
    }
  }

  def e3: MatchResult[Seq[Card]] = {
    val (laidDown, remaining) = layDownCards(Deck(singleDeck), 12)
    (laidDown.cards must haveSize(12)) and (remaining.cards must haveSize(
      singleDeck.length - 12))
  }

  def p4: ScalaCheckFunction3[Card, Card, Card, MatchResult[Card]] = prop {
    (c1: Card, c2: Card, c3: Card) =>
      val d = Deck(Seq(c1, c2, c3))
      val h = pickCards(d)(0, 1, 2)
      (h.c1 must beEqualTo(c1)) and (h.c2 must beEqualTo(c2)) and (h.c3 must beEqualTo(
        c3))
  }

}
