package halfpipe;

public class Player
{
	//Die Handkarten des jeweiligen Spielers
	private CardStack handCards;
	
	//Der Ablagestapel des jeweiligen Spielers
	private CardStack wonCards;
	

	private String name;
	
	
	public Player(String setName)
	{
		handCards = new CardStack("Handkarten von " + setName);
		wonCards = new CardStack("Ablagestapel von " + setName);
		name = setName;
	}

	
	public CardStack getHandCards()
	{
		return handCards;
	}
	
	public CardStack getWonCards()
	{
		return wonCards;
	}
	
	
	
}