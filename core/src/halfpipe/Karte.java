package halfpipe;

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
	
	@Override public String toString() {
		return farbe.name + "-" + wert.name;
	}

}