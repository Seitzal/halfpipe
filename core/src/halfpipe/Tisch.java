package halfpipe;

import java.util.Collections;

public class Tisch {
	//Der Skat, wie er leibt und lebt
	private Stapel skat;
	
	//Der mittlere Stichstapel
	private Stapel stiche;
	
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
		stiche = new Stapel("Stiche");
	}
	
	public void distributeToPlayersAtTable(Stapel kartenstapel) {
		for (int i=0; i<3; i++)
		{
			hoeren.getHandCards().addCard(kartenstapel.getCardAtPosition(0));
			kartenstapel.removeCard(0);
		}
		for (int i=0; i<3; i++)
		{
			sagen.getHandCards().addCard(kartenstapel.getCardAtPosition(0));
			kartenstapel.removeCard(0);
		}
		for (int i=0; i<3; i++)
		{
			geben.getHandCards().addCard(kartenstapel.getCardAtPosition(0));
			kartenstapel.removeCard(0);
		}
		
		for (int i=0; i<2; i++)
		{
			skat.addCard(kartenstapel.getCardAtPosition(0));
			kartenstapel.removeCard(0);
		}		
		
		for (int i=0; i<4; i++)
		{
			hoeren.getHandCards().addCard(kartenstapel.getCardAtPosition(0));
			kartenstapel.removeCard(0);
		}
		for (int i=0; i<4; i++)
		{
			sagen.getHandCards().addCard(kartenstapel.getCardAtPosition(0));
			kartenstapel.removeCard(0);
		}
		for (int i=0; i<4; i++)
		{
			geben.getHandCards().addCard(kartenstapel.getCardAtPosition(0));
			kartenstapel.removeCard(0);
		}
		
		for (int i=0; i<3; i++)
		{
			hoeren.getHandCards().addCard(kartenstapel.getCardAtPosition(0));
			kartenstapel.removeCard(0);
		}
		for (int i=0; i<3; i++)
		{
			sagen.getHandCards().addCard(kartenstapel.getCardAtPosition(0));
			kartenstapel.removeCard(0);
		}
		for (int i=0; i<3; i++)
		{
			geben.getHandCards().addCard(kartenstapel.getCardAtPosition(0));
			kartenstapel.removeCard(0);
		}
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
				new Karte(farbe, wert, kartendeck);
			}
		}
		return kartendeck;
	}
	
	public void shuffleCards(Stapel kartenstapel) {
		Collections.shuffle(kartenstapel.getList());
	}
}
