package halfpipe

sealed abstract class Kartentyp(
  val wert: Int, 
  val staerke: Int, 
  val nullStaerke: Int) {

  def kontextStaerke(implicit spiel: Spiel): Int = spiel match {
    case Null => nullStaerke
    case _ => staerke
  }

}

case object Sieben extends Kartentyp(0, 1, 1)
case object Acht extends Kartentyp(0, 2, 2)
case object Neun extends Kartentyp(0, 3, 3)
case object Dame extends Kartentyp(3, 4, 6)
case object Koenig extends Kartentyp(4, 5, 7)
case object Zehn extends Kartentyp(10, 6, 4)
case object Ass extends Kartentyp(11, 7, 8)
case object Bube extends Kartentyp(2, 8, 5)

object Kartentyp {
  def alle = List(Sieben, Acht, Neun, Dame, Koenig, Zehn, Ass, Bube)
}
