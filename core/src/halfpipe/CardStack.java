package halfpipe;

import java.util.ArrayList;

/* Kann für beliebige Sachen verwendet werden:
   Eine Spielerhand, den Skat, den Stapel in der Mitte des Tisches, die Ablagestapel etc.*/
public class CardStack {

	//Der Kartenstapel als Arraylist
	private ArrayList<Card> stack;
	private String name;

	public CardStack(String setName)
	{
		stack=new ArrayList<Card>();
		name = setName;
	}

	public void addCard(Card karte)
	{
		stack.add(karte);

	}

	// public void removeCard(Card karte)
	// {
	// 	// Übergibt eine Karte und durchsucht den Kartenstapel nach dieser Karte, um sie aus dem Stapel zu entfernen
	// 	for(int i = 0; i < this.getSize(); i++)
	// 	{
	// 		if (stack.get(i).getId() == karte.getId())
	// 		{
	// 			stack.remove(i);
	// 		}
	// 	}
	// }

	public void removeCard(int atPosition)
	{
		// Entfernt eine Karte aus dem Stapel anhand der Position
		stack.remove(atPosition);
	}

	public Card getCardAtPosition(int atPosition)
	{
		return stack.get(atPosition);
	}

	// public int getPositionOfCard(Card karte)
	// {
	// 	// Falls -1 zurückgegeben wird, ist die Karte nicht im Kartenstapel
	// 	int position = -1;
	// 	for(int i = 0; i < this.getSize(); i++)
	// 	{
	// 		if (stack.get(i).getId() == karte.getId())
	// 		{
	// 			position = i;
	// 		}
	// 	}
	// 	return position;
	// }

	public ArrayList<Card> getList()
	{
		//Die Arraylist dieser Instanz
		return stack;
	}

	// public void printContentId()
	// {
	// 	for(Card oneItem: stack)
	// 	{
	// 		oneItem.printId();
	// 	}
	// }

	public void printContentName() {
		System.out.println(name + ":");
		for(Card card : stack)
		{
			System.out.println(card);
		}
	}

	// Der Zählwert des Stapels für das Zählen am Ende
	public int getSum() {
		// System.out.println(name + ":");
		int sum = 0;
		for(Card card : stack) {
			sum += card.value.value;
		}
		return sum;
	}

	//Die Anzahl der Karten, die sich in diesem Kartenstapel befinden
	public int getSize() {
		return stack.size();
	}

}
