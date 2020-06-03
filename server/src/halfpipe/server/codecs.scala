package halfpipe.server

import halfpipe._
import upickle.default._
import ujson.{Obj, Arr}
import collection.JavaConverters._

package object codecs {

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

}
