package halfpipe;

public class Main {

	public static void main (String[] args) {
		Spieler player1 = new Spieler("Alex S.");
		Spieler player2 = new Spieler("Jakob");
		Spieler player3 = new Spieler("Stephan");
		Tisch tableAtHotz = new Tisch(player1, player2, player3);
		Stapel cardDeck = tableAtHotz.createCardDeck();
		tableAtHotz.shuffleCards(cardDeck);
		tableAtHotz.deal(cardDeck);
		tableAtHotz.printAllCardStacks();
	}

}