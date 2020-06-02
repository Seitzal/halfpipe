package halfpipe;

public class Spieler
{
	//Die Handkarten des jeweiligen Spielers
	private Stapel handCards;
	
	//Der Ablagestapel des jeweiligen Spielers
	private Stapel wonCards;
	

	private String name;
	
	
	public Spieler(String setName)
	{
		handCards = new Stapel("Handkarten von " + setName);
		wonCards = new Stapel("Ablagestapel von " + setName);
		name = setName;
	}

	
	public Stapel getHandCards()
	{
		return handCards;
	}
	
	public Stapel getWonCards()
	{
		return wonCards;
	}
	
	
	
}