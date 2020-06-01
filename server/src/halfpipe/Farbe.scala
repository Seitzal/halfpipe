package halfpipe

sealed abstract class Farbe(val wert: Int)

case object Karo extends Farbe(9)
case object Herz extends Farbe(10)
case object Pik extends Farbe(11)
case object Kreuz extends Farbe(12)

object Farbe {
  def alle = List(Karo, Herz, Pik, Kreuz)
}
