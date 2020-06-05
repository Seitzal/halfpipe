package halfpipe;

import java.util.Collections;

public class Tisch {
	//Der Skat, wie er leibt und lebt
	private Stapel skat;
	
	//Der mittlere Stichstapel
	private Stapel stich;
	//Aufpassen, reizindex != reizwert, reizwert bezeichnet das was schon angesagt wurde, und der Index die Stelle in der Reizwerttabelle, die als nächstes kommt
	private int reizindex;
	private int reizwert;
	private int runde;
	private int spiel;
	//Die nachfolgenden Variablen sind personenspezifisch, d.h. sie ändern sich nicht im Verlauf einer Runde
	private final Spieler spieler1;
	private final Spieler spieler2;
	private final Spieler spieler3;
	private static final int[] reizwerttabelle = new int[]{18,20,22,23,24,27,30,33,35,36,40,44,45,46,48,50,54,55,59,60,63,66,70,72,77,80,81,84,88,90,96,99,100,108,110,117,120,121,126,130,132,135,140,143,144,150,153,154,156,160,162,165,168,170,176,180,187,192,198,204,216,240,264};
	
	
	//Die nachfolgenden Variablen sind spielspezifisch, d.h. sie rotieren während einer Runde durch
	//In der ersten Runde ist firstPlayer=geben, secondPlayer=hören, thirdPlayer = sagen
	private Spieler geben;
	private Spieler hoeren;
	private Spieler sagen;
	
	public Tisch(Spieler spieler1, Spieler spieler2, Spieler spieler3) {
		this.spieler1 = spieler1;
		this.spieler2 = spieler2;
		this.spieler3 = spieler3;
		
		geben = spieler1;
		hoeren = spieler2;
		sagen = spieler3;
		
		runde=1;
		spiel=1;
		
		skat = new Stapel("Skat");
		stich = new Stapel("Stiche");
	}
	
	public void deal(Stapel kartenstapel) {
		kartenstapel.moveN(hoeren.getHandCards(), 3);
		kartenstapel.moveN(sagen.getHandCards(), 3);
		kartenstapel.moveN(geben.getHandCards(), 3);
		kartenstapel.moveN(skat, 2);
		kartenstapel.moveN(hoeren.getHandCards(), 4);
		kartenstapel.moveN(sagen.getHandCards(), 4);
		kartenstapel.moveN(geben.getHandCards(), 4);
		kartenstapel.moveN(hoeren.getHandCards(), 3);
		kartenstapel.moveN(sagen.getHandCards(), 3);
		kartenstapel.moveN(geben.getHandCards(), 3);
	}
	
	public void printAllCardStacks() {
		hoeren.getHandCards().printContentName();
		sagen.getHandCards().printContentName();
		geben.getHandCards().printContentName();
		skat.printContentName();
	}
	
	public Stapel createCardDeck() {
		Stapel kartendeck = new Stapel("Kartendeck");
    for (Farbe farbe : Farbe.values()) {
			for (Kartenwert wert : Kartenwert.values()) {
				kartendeck.addCard(new Karte(farbe, wert));
			}
		}
		return kartendeck;
	}
	
	public void shuffleCards(Stapel kartenstapel) {
		Collections.shuffle(kartenstapel.getList());
	}
	
	public Spieler Reizen(){
		//gibt den Alleinspieler zurück; falls niemand spielen will, wird null zurückgegeben (->Ramsch)
		Spieler ansagen = sagen;
		Spieler mitgehen = hoeren;
		reizindex=0;
		reizwert=0;
		String antwort;
		for (int i = reizindex; i<reizwerttabelle.length; i++) {
			reizindex = i;
			
			/* Hier später durch Userinterface-Eingabe ersetzen: */
			System.out.println("Spieler " + ansagen.name + ", wollen Sie " + reizwerttabelle[i] + " ansagen? [y/n]");
			antwort = System.console().readLine();
			
			if (antwort.equals("n")){
				ansagen = geben;
				break;
			}
			reizwert = reizwerttabelle[i];
			
			/* Hier später durch Userinterface-Eingabe ersetzen: */
			System.out.println("Spieler " + mitgehen.name + ", wollen Sie bei " + reizwerttabelle[i] + " mitgehen? [y/n]");
			antwort = System.console().readLine();
			
			
			if (antwort.equals("n")){
				reizindex = reizindex + 1;
				ansagen = geben;
				mitgehen = sagen;
				break;
			}
		}
		for (int i = reizindex; i<reizwerttabelle.length; i++) {
			reizindex = i;
			
			/* Hier später durch Userinterface-Eingabe ersetzen: */
			System.out.println("Spieler " + ansagen.name + ", wollen Sie " + reizwerttabelle[i] + " ansagen? [y/n]");
			antwort = System.console().readLine();
			
			if (antwort.equals("n")){
				if (reizwert==0){
					
					/* Hier später durch Userinterface-Eingabe ersetzen: */
					System.out.println("Spieler " + mitgehen.name + ", wollen Sie das Spiel fuer 18 spielen? [y/n]");
					antwort = System.console().readLine();
					
					if (antwort.equals("n")){
						System.out.println("Es wird Ramsch gespielt!");
						return null;
					}
					reizwert = 18;
					
					/* Hier später durch Userinterface-Ausgabe ersetzen: */
					System.out.println("Spieler " + mitgehen.name + " spielt das Spiel fuer " + reizwert);
					return mitgehen;
				} else {
					
					/* Hier später durch Userinterface-Ausgabe ersetzen: */
					System.out.println("Spieler " + mitgehen.name + " spielt das Spiel fuer " + reizwert);
					return mitgehen;
				}
			
			}
			
			reizwert = reizwerttabelle[i];
			
			/* Hier später durch Userinterface-Eingabe ersetzen: */
			System.out.println("Spieler " + mitgehen.name + ", wollen Sie bei " + reizwerttabelle[i] + " mitgehen? [y/n]");
			antwort = System.console().readLine();
			
			if (antwort.equals("n")){
				
				/* Hier später durch Userinterface-Ausgabe ersetzen: */
				System.out.println("Spieler " + ansagen.name + " spielt das Spiel fuer " + reizwert);
				return ansagen;
			}
		}
		
		/* Hier später durch Userinterface-Ausgabe ersetzen: */
		System.out.println("Spieler " + mitgehen.name + " spielt das Spiel fuer " + reizwert);
		return mitgehen;		
	}
	
	public void reset()  {
		spiel = spiel + 1;
		
		//Leert alle Kartenstapel
		skat.empty();
		stich.empty();
		spieler1.getHandCards().empty();
		spieler1.getWonCards().empty();
		spieler2.getHandCards().empty();
		spieler2.getWonCards().empty();
		spieler3.getHandCards().empty();
		spieler3.getWonCards().empty();
		
		
		if (spiel%3==0){
			geben = spieler3;
			hoeren=spieler1;
			sagen=spieler2;
		}
		if (spiel%3==1){
			geben = spieler1;
			hoeren = spieler2;
			sagen = spieler3;			
			runde = runde + 1
		}
		if (spiel%3==2){
			geben=spieler2;
			hoeren=spieler3;
			sagen=spieler1;
		}
	}
}
