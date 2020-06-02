package halfpipe;

public class Karte {

	/* 0=Kreuz  1=Pik  2=Herz  3=Karo*/
	public final Farbe farbe;
	
	/* Der Wert der Karte, also 7,8,9,Bube, ...*/
	public final Kartenwert wert;
	
	/*Der Kartenstapel, in dem sich die Karte aktuell befindet (eine Karte kann nur in einem Kartenstapel gleichzeitig sein)*/
	private Stapel currentStack;

	/* Erzeugt eine Karte mithilfe von Farbe und Wert */
	public Karte(Farbe farbe, Kartenwert wert, Stapel cardStack) {
		this.farbe = farbe;
		this.wert = wert;
		currentStack = cardStack;

		//Die Karte ist soweit erzeugt, aber der jeweilige Kartenstapel muss auch noch die Referenz dieser Instanz bekommen
		//damit sich auf die Karte auch vom Kartenstapel zugreifen lässt
		currentStack.addCard(this);
	}

	public Stapel getCurrentStack() {
		return currentStack;
	}
	
	@Override public String toString() {
		return farbe.name + "-" + wert.name;
	}

}