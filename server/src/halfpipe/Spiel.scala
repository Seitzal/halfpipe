package halfpipe

sealed abstract class Spiel

final case class Farbspiel(farbe: Farbe) extends Spiel
case object Grand extends Spiel
case object Null extends Spiel
case object Ramsch extends Spiel 
