package halfpipe;

import java.util.ArrayList;

/* Kann für beliebige Sachen verwendet werden:
   Eine Spielerhand, den Skat, den Stapel in der Mitte des Tisches, die Ablagestapel etc.*/
public class Stapel {

	//Der Kartenstapel als Arraylist
	private ArrayList<Karte> karten;
	public final String name;

	public Stapel(String name) {
		karten=new ArrayList<Karte>();
		this.name = name;
	}

	public void addCard(Karte karte) {
		karten.add(karte);
	}

	public void addCards(ArrayList<Karte> karten) {
		for (Karte karte: karten) {
			this.karten.add(karte);
		}
	}

	public Karte drawOne() {
		return karten.remove(karten.size() - 1);
	}

	public ArrayList<Karte> drawN(int n) {
		ArrayList<Karte> buffer = new ArrayList<Karte>();
		for(int i = 0; i < n; i++) {
			buffer.add(drawOne());
		}
		return buffer;
	}

	public void moveN(Stapel wohin, int n) {
		for(int i = 0; i < n; i++) {
			wohin.addCard(drawOne());
		}
	}

	public Karte removeAtIndex(int index) {
		return karten.remove(index);
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
	
	public void empty() {
		karten.clear();
	}

	@Override public boolean equals(Object o) {
		if (o instanceof Stapel) {
			Stapel anderer = (Stapel) o;
			if (!name.equals(anderer.name) || !this.getList().equals(anderer.getList()))
				return false;
			else return true;
		} else return false;
	}

}
