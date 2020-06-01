import halfpipe._
import org.scalatest._
import funsuite._

class Spiellogik extends AnyFunSuite {

  test("Neues Deck") {
    val deck = new Deck
    assert(deck.size == 32)
    val karten = deck.draw(32)
    assert(deck.size == 0)
    assert(karten.toSet.size == karten.size)
  }

}
