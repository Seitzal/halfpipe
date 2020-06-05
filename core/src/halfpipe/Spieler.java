package halfpipe;

public class Spieler
{
	//Die Handkarten des jeweiligen Spielers
	private Stapel handCards;
	
	//Der Ablagestapel des jeweiligen Spielers
	private Stapel wonCards;
	

	public String name;
	
	
	public Spieler(String name)
	{
		handCards = new Stapel("Handkarten von " + name);
		wonCards = new Stapel("Ablagestapel von " + name);
		this.name = name;
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