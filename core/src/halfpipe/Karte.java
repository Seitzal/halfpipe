package halfpipe;

import static halfpipe.Kartenwert.*;

public class Karte {

	/* 0=Kreuz  1=Pik  2=Herz  3=Karo*/
	public final Farbe farbe;
	
	/* Der Wert der Karte, also 7,8,9,Bube, ...*/
	public final Kartenwert wert;

	/* Erzeugt eine Karte mithilfe von Farbe und Wert */
	public Karte(Farbe farbe, Kartenwert wert) {
		this.farbe = farbe;
		this.wert = wert;
	}

	public int staerkeIn(Spiel spiel) {
		return spiel.istNull ? wert.staerkeNull : wert.staerkeNormal;
	}

	// Prüft, ob eine Karte im gegebenen Spiel Trumpf ist.
	public Boolean istTrumpf(Spiel spiel) {
		if (spiel.istFarbspiel)
			return wert == BUBE || farbe == spiel.trumpffarbe;
		else if (spiel.istGrand)
			return wert == BUBE;
		else return false;
	}

	// Prüft, ob eine Karte im gegebenen Spiel eine andere schlägt,
	// unter der Annahme, dass bekannt wurde.
	public Boolean schlägt(Karte andere, Spiel spiel) {
		if (this.istTrumpf(spiel))
			if (andere.istTrumpf(spiel))
				if (this.wert == BUBE)
					if (andere.wert == BUBE)
						return this.farbe.wert > andere.farbe.wert; // Höherer Bube
					else return true; // Bube > Farbtrumpf
				else if (andere.wert != BUBE)
					return this.staerkeIn(spiel) > andere.staerkeIn(spiel); // Höherer Farbtrumpf
				else return false; // Farbtrumpf < Bube
			else return true; // Trumpf > Nicht-Trumpf
		else if (andere.istTrumpf(spiel))
			return false; // Nicht-Trumpf < Trumpf
		else return this.staerkeIn(spiel) > andere.staerkeIn(spiel); // Höherer Nicht-Trumpf
	}
	
	@Override public String toString() {
		return farbe.name + "-" + wert.name;
	}

	@Override public boolean equals(Object o) {
		if (o instanceof Karte) {
			Karte andere = (Karte) o;
			return farbe == andere.farbe && wert == andere.wert;
		} else return false;
	}

}