package halfpipe;

import java.util.ArrayList;

/* Kann für beliebige Sachen verwendet werden:
   Eine Spielerhand, den Skat, den Stapel in der Mitte des Tisches, die Ablagestapel etc.*/
public class Stapel {

	//Der Kartenstapel als Arraylist
	private ArrayList<Karte> karten;
	private String name;

	public Stapel(String name) {
		karten=new ArrayList<Karte>();
		this.name = name;
	}

	public void addCard(Karte karte) {
		karten.add(karte);
	}

	// Entfernt eine Karte aus dem Stapel anhand der Position
	public void removeCard(int atPosition) {
		karten.remove(atPosition);
	}

	public Karte getCardAtPosition(int atPosition) {
		return karten.get(atPosition);
	}

	// Die Arraylist dieser Instanz
	public ArrayList<Karte> getList() {
		return karten;
	}

	public void printContentName() {
		System.out.println(name + ":");
		for(Karte card : karten)
		{
			System.out.println(card);
		}
	}

	// Der Zählwert des Stapels für das Zählen am Ende
	public int getSum() {
		int sum = 0;
		for(Karte card : karten) {
			sum += card.wert.punkte;
		}
		return sum;
	}

	//Die Anzahl der Karten, die sich in diesem Kartenstapel befinden
	public int getSize() {
		return karten.size();
	}

}
