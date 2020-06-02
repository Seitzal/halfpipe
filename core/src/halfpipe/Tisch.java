package halfpipe;

import java.util.Collections;

public class Tisch {
	//Der Skat, wie er leibt und lebt
	private Stapel skat;
	
	//Der mittlere Stichstapel
	private Stapel stich;
	
	//Die nachfolgenden Variablen sind personenspezifisch, d.h. sie ändern sich nicht im Verlauf einer Runde
	private final Spieler spieler1;
	private final Spieler spieler2;
	private final Spieler spieler3;
	
	//Die nachfolgenden Variablen sind spielspezifisch, d.h. sie rotieren während einer Runde durch
	//In der ersten Runde ist firstPlayer=geben, secondPlayer=hören, thirdPlayer = sagen
	private Spieler geben;
	private Spieler hoeren;
	private Spieler sagen;
	
	public Tisch(Spieler spieler1, Spieler spieler2, Spieler spieler3) {
		this.spieler1 = spieler1;
		this.spieler2 = spieler2;
		this.spieler3 = spieler3;
		
		geben = spieler1;
		hoeren = spieler2;
		sagen = spieler3;
		
		skat = new Stapel("Skat");
		stich = new Stapel("Stiche");
	}
	
	public void deal(Stapel kartenstapel) {
		kartenstapel.moveN(hoeren.getHandCards(), 3);
		kartenstapel.moveN(sagen.getHandCards(), 3);
		kartenstapel.moveN(geben.getHandCards(), 3);
		kartenstapel.moveN(skat, 2);
		kartenstapel.moveN(hoeren.getHandCards(), 4);
		kartenstapel.moveN(sagen.getHandCards(), 4);
		kartenstapel.moveN(geben.getHandCards(), 4);
		kartenstapel.moveN(hoeren.getHandCards(), 3);
		kartenstapel.moveN(sagen.getHandCards(), 3);
		kartenstapel.moveN(geben.getHandCards(), 3);
	}
	
	public void printAllCardStacks() {
		hoeren.getHandCards().printContentName();
		sagen.getHandCards().printContentName();
		geben.getHandCards().printContentName();
		skat.printContentName();
	}
	
	public Stapel createCardDeck() {
		Stapel kartendeck = new Stapel("Kartendeck");
    for (Farbe farbe : Farbe.values()) {
			for (Kartenwert wert : Kartenwert.values()) {
				kartendeck.addCard(new Karte(farbe, wert));
			}
		}
		return kartendeck;
	}
	
	public void shuffleCards(Stapel kartenstapel) {
		Collections.shuffle(kartenstapel.getList());
	}
}
