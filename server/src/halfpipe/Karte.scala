package halfpipe

final case class Karte(farbe: Farbe, typ: Kartentyp) {

    // Prüft, ob eine Karte Trumpf ist.
    // Im Nullspiel immer false.
    def istTrumpf(implicit spiel: Spiel): Boolean = 
      spiel match {
        case Farbspiel(trumpffarbe) => typ == Bube || farbe == trumpffarbe
        case Null => false
        case _ => typ == Bube
      }

    // Prüft, ob eine Karte eine andere Karte schlägt.
    // Farbzwang wird hier nicht geprüft, es wird von Bekenntnis oder Übertrumpfen ausgegangen.
    def schlaegt(andere: Karte)(implicit spiel: Spiel): Boolean =
      if (istTrumpf)
        if (andere.istTrumpf)
          if (typ == Bube && andere.typ == Bube) farbe.wert > andere.farbe.wert
          else typ.kontextStaerke > andere.typ.kontextStaerke
        else true
      else if(!andere.istTrumpf) typ.kontextStaerke > andere.typ.kontextStaerke
      else false

}

object Karte {

  def alle: List[Karte] = 
    for {
      farbe <- Farbe.alle
      typ <- Kartentyp.alle
    } yield Karte(farbe, typ)

}
