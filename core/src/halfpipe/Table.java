package halfpipe;

import java.util.Collections;

public class Table
{
	//Der Skat, wie er leibt und lebt
	private CardStack skat;
	
	//Der mittlere Stichstapel
	private CardStack middleStack;
	
	//Die nachfolgenden Variablen sind personenspezifisch, d.h. sie ändern sich nicht im Verlauf einer Runde
	private Player firstPlayer;
	private Player secondPlayer;
	private Player thirdPlayer;
	
	//Die nachfolgenden Variablen sind spielspezifisch, d.h. sie rotieren während einer Runde durch
	//In der ersten Runde ist firstPlayer=geben, secondPlayer=hören, thirdPlayer = sagen
	private Player geben;
	private Player hoeren;
	private Player sagen;
	
	public Table(Player player1, Player player2, Player player3)
	{
		firstPlayer = player1;
		secondPlayer = player2;
		thirdPlayer = player3;
		
		geben = firstPlayer;
		hoeren = secondPlayer;
		sagen = thirdPlayer;
		
		skat = new CardStack("Skat");
		middleStack = new CardStack("aktueller Stich");
	}
	
	public void distributeToPlayersAtTable(CardStack kartenstapel)
	{
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
	
	public void printAllCardStacks()
	{
		hoeren.getHandCards().printContentName();
		sagen.getHandCards().printContentName();
		geben.getHandCards().printContentName();
		skat.printContentName();
	}
	
	public CardStack createCardDeck()
	{
		CardStack kartendeck = new CardStack("Kartendeck");
    for (Farbe farbe : Farbe.values()) {
			for (Kartenwert wert : Kartenwert.values()) {
				new Card(farbe, wert, kartendeck);
			}
		}
		return kartendeck;
	}
	
	public void shuffleCards(CardStack kartenstapel)
	{
		Collections.shuffle(kartenstapel.getList());
		
	}
}