package org.vincibean.game.set.service

import eu.timepit.refined.auto._
import eu.timepit.refined.numeric._
import eu.timepit.refined.scalacheck.NumericInstances
import org.scalacheck.Arbitrary
import org.specs2.matcher.MatchResult
import org.specs2.scalacheck.ScalaCheckFunction3
import org.specs2.specification.core.SpecStructure
import org.specs2.{ScalaCheck, Specification}
import org.vincibean.game.set.lexical._
import org.vincibean.game.set.{ArbitraryCards, Natural}

class DeckServiceSpec
    extends Specification
    with ScalaCheck
    with NumericInstances
    with DeckService
    with ArbitraryCards {
  override def is: SpecStructure =
    s2"""
        deckFrom must
          return a full (with the appropriate number of cards) deck if a number greaten than 0 is given $e1
        layDownCards must
          use the right cardinality for laid down cards as well as remaining cards in the Deck $e2
          consume the whole Deck if a big number is provided $e3
        pickCards must
          return the expected cards $p4
          return None if big numbers are provided $p5
          return None if the same number is provided $p6
          return None if two same numbers are provided $p7
      """

  def e1: MatchResult[Seq[Card]] = deckFrom(1).cards must haveSize(81)

  implicit val arbitraryDeck: Arbitrary[Deck] = Arbitrary(
    Arbitrary.arbitrary[Natural].map(deckFrom))

  def e2: MatchResult[Seq[Card]] = {
    val (laidDown, remaining) = layDownCards(Deck(singleDeck), 12)
    (laidDown.cards must haveSize(12)) and (remaining.cards must haveSize(
      singleDeck.length - 12))
  }

  def e3: MatchResult[Seq[Card]] = {
    val (laidDown, remaining) = layDownCards(Deck(singleDeck), 999)
    (laidDown.cards must haveSize(singleDeck.length)) and (remaining.cards must beEmpty)
  }

  def p4: ScalaCheckFunction3[Card, Card, Card, MatchResult[Option[Hand]]] =
    prop { (c1: Card, c2: Card, c3: Card) =>
      val d = Deck(Seq(c1, c2, c3))
      val sh = pickCards(d)(0, 1, 2)
      sh must beSome.like {
        case (h: Hand) =>
          (h.c1 must beEqualTo(c1)) and (h.c2 must beEqualTo(c2)) and (h.c3 must beEqualTo(
            c3))
      }
    }

  def p5: ScalaCheckFunction3[Card, Card, Card, MatchResult[Option[Hand]]] =
    prop { (c1: Card, c2: Card, c3: Card) =>
      val d = Deck(Seq(c1, c2, c3))
      val sh = pickCards(d)(90, 91, 92)
      sh must beNone
    }

  def p6: ScalaCheckFunction3[Card, Card, Card, MatchResult[Option[Hand]]] =
    prop { (c1: Card, c2: Card, c3: Card) =>
      val d = Deck(Seq(c1, c2, c3))
      val sh = pickCards(d)(0, 0, 0)
      sh must beNone
    }

  def p7: ScalaCheckFunction3[Card, Card, Card, MatchResult[Option[Hand]]] =
    prop { (c1: Card, c2: Card, c3: Card) =>
      val d = Deck(Seq(c1, c2, c3))
      val sh = pickCards(d)(0, 0, 1)
      sh must beNone
    }

}
