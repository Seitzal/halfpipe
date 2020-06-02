package halfpipe;

public class Card
{
	/* 0=Kreuz  1=Pik  2=Herz  3=Karo*/
	private int color;
	
	/* Der Wert der Karte, also 7,8,9,Bube, ...
	   0=7  1=8  2=9  3=10  4=Bube  5=Dame  6=König  7=Ass*/
	private int value;
	
	/* Der Punktewert der Karte beim Zählen am Ende des Spiels
	   0=7  0=8  0=9  10=10 2=Bube  3=Dame  4=König  11=Ass*/
	private int pointValue;
	
	/* Eine einmalige ID, z.B. geeignet zum Loopen beim Erzeugen des Kartenstapels
	   0=Kreuz-Sieben .. 7=Kreuz-Ass
	   8=Pik-Sieben   .. 15=Pik-Ass
	   16=Herz-Sieben .. 23=Herz-Ass
	   24=Karo-Sieben .. 31=Karo-Ass*/
	private int id;
	
	/*Der Kartenstapel, in dem sich die Karte aktuell befindet (eine Karte kann nur in einem Kartenstapel gleichzeitig sein)*/
	private CardStack currentStack;
	
	
	public Card(int setColor, int setValue, CardStack setCardStack)
	/* Erzeugt eine Karte mithilfe von Farbe und Wert */
	{
		color = setColor;
		value = setValue;
		id = setColor * 8 + setValue;
        switch (value)
		{
			case 3:
				pointValue = 10; 
				break;
			case 4:
				pointValue = 2; 
				break;
			case 5:
				pointValue = 3; 
				break;
			case 6:
				pointValue = 4; 
				break;
			case 7:
				pointValue = 11; 
				break;
			default:
				pointValue = 0;
				break;
		}
		currentStack = setCardStack;
		
		//Die Karte ist soweit erzeugt, aber der jeweilige Kartenstapel muss auch noch die Referenz dieser Instanz bekommen
		//damit sich auf die Karte auch vom Kartenstapel zugreifen lässt
		currentStack.addCard(this);
	}
	
	public Card(int setId, CardStack setCardStack)
	/* Erzeugt eine Karte mithilfe von ihrer ID */
	{
		color = (int) (setId / 8);
		value = setId % 8;
		id = setId;
        switch (value)
		{
			case 3:
				pointValue = 10; 
				break;
			case 4:
				pointValue = 2; 
				break;
			case 5:
				pointValue = 3; 
				break;
			case 6:
				pointValue = 4; 
				break;
			case 7:
				pointValue = 11; 
				break;
			default:
				pointValue = 0;
				break;
		}
		currentStack = setCardStack;		
		currentStack.addCard(this);
	}
	
	public int getColor()
	{
		return color;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public int getId()
	{
		return id;
	}
	
	public int getPointValue()
	{
		return pointValue;
	}

	public CardStack getCurrentStack()
	{
		return currentStack;
	}
	
	public void printId()
	{
		System.out.println(id);
	}
	
	public void printName()
	{
        switch (color)
		{
			case 0:
				System.out.print("Kreuz-"); 
				break;
			case 1:
				System.out.print("Pik-"); 
				break;
			case 2:
				System.out.print("Herz-"); 
				break;
			case 3:
				System.out.print("Karo-"); 
				break;
			default:
				System.out.print("Fehlerhafte Zuweisung der Farbe ");
				break;
		}
        switch (value)
		{
			case 0:
				System.out.println("Sieben"); 
				break;
			case 1:
				System.out.println("Acht"); 
				break;
			case 2:
				System.out.println("Neun"); 
				break;
			case 3:
				System.out.println("Zehn"); 
				break;
			case 4:
				System.out.println("Bube"); 
				break;
			case 5:
				System.out.println("Dame"); 
				break;
			case 6:
				System.out.println("K\u00F6nig"); 
				break;
			case 7:
				System.out.println("Ass"); 
				break;
			default:
				System.out.println("Fehlerhafte Zuweisung des Wertes");
				break;
		}
	/* Ä \u00C4
	   Ö \u00D6
	   Ü \u00DC
       ä \u00E4
       ö \u00F6
       ü \u00FC
    */		
	}
}