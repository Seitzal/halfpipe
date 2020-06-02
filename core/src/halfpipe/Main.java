package halfpipe;

public class Main
{

	public static void main (String[] args)
	{
		Player player1 = new Player("Alex S.");
		Player player2 = new Player("Jakob");
		Player player3 = new Player("Stephan");
		Table tableAtHotz = new Table(player1, player2, player3);
		CardStack cardDeck = tableAtHotz.createCardDeck();
		tableAtHotz.shuffleCards(cardDeck);
		tableAtHotz.distributeToPlayersAtTable(cardDeck);
		tableAtHotz.printAllCardStacks();
	}



}