package halfpipe.server

import halfpipe._
import upickle.default._
import ujson.{Obj, Arr, True, False, Value}
import collection.JavaConverters._

package object codecs {

  def bv(bool: Boolean): Value =
    if (bool) True else False

  implicit val farbeRW: ReadWriter[Farbe] = 
    readwriter[String].bimap(_.name, Farbe.fromName)
  
  implicit val wertRW: ReadWriter[Kartenwert] = 
    readwriter[String].bimap(_.name, Kartenwert.fromName)
  
  private def karteToObj(karte: Karte) = Obj(
    "farbe" -> karte.farbe.name,
    "wert" -> karte.wert.name
  )

  private def objToKarte(obj: Obj) = new Karte(
    Farbe.fromName(obj("farbe").str),
    Kartenwert.fromName(obj("wert").str)
  )

  implicit val karteRW: ReadWriter[Karte] =
    readwriter[Obj].bimap(karteToObj, objToKarte)

  private def stapelToObj(stapel: Stapel) = Obj(
    "name" -> stapel.name,
    "karten" -> Arr.from(stapel.getList.asScala.map(karteToObj))
  )

  private def objToStapel(obj: Obj) = {
    val stapel = new Stapel(obj("name").str)
    for (karte <- obj("karten").arr.map(_.asInstanceOf[Obj]).map(objToKarte))
      stapel.addCard(karte)
    stapel
  }

  implicit val stapelRW: ReadWriter[Stapel] =
    readwriter[Obj].bimap(stapelToObj, objToStapel)

  private def spielToObj(spiel: Spiel) = Obj(
    "bubenFaktor" -> spiel.bubenFaktor,
    "istNull" -> bv(spiel.istNull),
    "istRamsch" -> bv(spiel.istRamsch),
    "istGrand" -> bv(spiel.istGrand),
    "istFarbspiel" -> bv(spiel.istFarbspiel),
    "trumpffarbe" -> spiel.trumpffarbe.name,
    "istHand" -> bv(spiel.istHand),
    "istSchneiderAngesagt" -> bv(spiel.istSchneiderAngesagt),
    "istSchwarzAngesagt" -> bv(spiel.istSchwarzAngesagt),
    "istOuvert" -> bv(spiel.istOuvert),
    "kontraStufe" -> spiel.kontraStufe,
    "geschoben" -> spiel.geschoben
  )

  private def objToSpiel(obj: Obj) = new Spiel(
    obj("bubenFaktor").num.toInt,
    obj("istNull").bool,
    obj("istRamsch").bool,
    obj("istGrand").bool,
    obj("istFarbspiel").bool,
    Farbe.fromName(obj("trumpffarbe").str),
    obj("istHand").bool,
    obj("istSchneiderAngesagt").bool,
    obj("istSchwarzAngesagt").bool,
    obj("istOuvert").bool,
    obj("kontraStufe").num.toInt,
    obj("geschoben").num.toInt
  )

  implicit val spielRW: ReadWriter[Spiel] =
    readwriter[Obj].bimap(spielToObj, objToSpiel)

}
