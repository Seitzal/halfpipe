package halfpipe;

public class Card {

	/* 0=Kreuz  1=Pik  2=Herz  3=Karo*/
	public final Farbe color;
	
	/* Der Wert der Karte, also 7,8,9,Bube, ...*/
	public final Kartenwert value;
	
	/*Der Kartenstapel, in dem sich die Karte aktuell befindet (eine Karte kann nur in einem Kartenstapel gleichzeitig sein)*/
	private CardStack currentStack;

	/* Erzeugt eine Karte mithilfe von Farbe und Wert */
	public Card(Farbe color, Kartenwert value, CardStack cardStack) {
		this.color = color;
		this.value = value;
		currentStack = cardStack;

		//Die Karte ist soweit erzeugt, aber der jeweilige Kartenstapel muss auch noch die Referenz dieser Instanz bekommen
		//damit sich auf die Karte auch vom Kartenstapel zugreifen lässt
		currentStack.addCard(this);
	}

	public CardStack getCurrentStack()
	{
		return currentStack;
	}
	
	@Override public String toString() {
		return color.name + "-" + value.name;
	}

}