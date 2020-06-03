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

}