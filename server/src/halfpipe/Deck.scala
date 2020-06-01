package halfpipe

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

final class Deck {

  private val karten = ArrayBuffer.from(Random.shuffle(Karte.alle))

  def draw(n: Int): List[Karte] = {
    val res = karten.take(n).toList
    karten.dropInPlace(n)
    res
  }

  def size: Int = 
    karten.length

}
