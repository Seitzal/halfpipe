import org.scalatest._
import funsuite._
import halfpipe._
import halfpipe.server.codecs._
import upickle.default._
import Farbe._
import Kartenwert._

class CodecTests extends AnyFunSuite {

  test("Codec für Karten") {
    val karte1 = new Karte(KARO, SIEBEN)
    assert(karte1 == read[Karte](write(karte1)))
  }

  test("Codec für Stapel") {
    val stapel1 = new Stapel("Ein Stapel!!!")
    stapel1.addCard(new Karte(KARO, SIEBEN))
    stapel1.addCard(new Karte(PIK, ASS))
    assert(stapel1 == read[Stapel](write(stapel1)))
  }

  test("Codec für Spiele") {
    val spiel1 = Spiel.farbspiel(2, KARO, true, true, false, false)
    assert(spiel1 == read[Spiel](write(spiel1)))
    val spiel2 = Spiel.grand(-3, false, false, false, false)
    assert(spiel2 == read[Spiel](write(spiel2)))
    val spiel3 = Spiel.nullspiel(true, true)
    assert(spiel3 == read[Spiel](write(spiel3)))
    val spiel4 = Spiel.ramsch(2)
    assert(spiel4 == read[Spiel](write(spiel4)))
  }

}