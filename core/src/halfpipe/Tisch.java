package halfpipe;

import java.util.NoSuchElementException;
import java.util.HashMap;

public class Tisch {

	// Der Skat, wie er leibt und lebt
	private final Stapel skat = new Stapel("Skat");
	
	// Der mittlere Stichstapel
	private final Stapel stich = new Stapel("Stiche");

	// Die nachfolgenden Variablen sind personenspezifisch, d.h. sie ändern sich 
	// nicht im Verlauf einer Runde
	private final Spieler spieler1;
	private final Spieler spieler2;
	private final Spieler spieler3;

	// Die nachfolgenden Variablen sind spielspezifisch, d.h. sie rotieren während 
	// einer Runde durch.
	private Spieler geben;
	private Spieler hoeren;
	private Spieler sagen;
	private Boolean hoerenGepasst;
	private Boolean sagenGepasst;
	private Boolean gebenGepasst;
	private Boolean reizphaseVorbei;
	private Boolean mitgegangen;
	private Spieler solist;
	private Boolean ramsch;
	private Spiel spiel;
	private int reizindex;
	private int reizwert;
	private int spielNr;

	// ---------------------------------------------------------------------------
	// HILFSFUNKTIONEN
	
	private static final int[] REIZWERTE = {
		18,20,22,23,24,27,30,33,35,36,40,44,45,46,48,50,54,55,59,60,63,66,70,72,77,
		80,81,84,88,90,96,99,100,108,110,117,120,121,126,130,132,135,140,143,144,
		150,153,154,156,160,162,165,168,170,176,180,187,192,198,204,216,240,264
	};

	public Spieler spieler(int nummer) {
		switch (nummer) {
			case 1: return spieler1;
			case 2: return spieler2;
			case 3: return spieler3;
			default: throw new NoSuchElementException();
		}
	}

	public int runde() {
		return 1 + ((spielNr - 1) / 3);
	}

	// ---------------------------------------------------------------------------
	// CALLBACKS FÜR REAKTION AUF ZUSTANDSÄNDERUNGEN

	// Liste von Callbacks, die bei Änderung des Zustands auszuführen sind
	private final HashMap<Integer, Runnable> stateChangeCallbacks = 
		new HashMap<>();
	
	// ID des nächsten registrierten Callbacks
	private int nextCallbackId = 0;

	// Registriert einen Callback und gibt eine unique ID zurück
	public int registerStateChangeCallback(Runnable callback) {
		stateChangeCallbacks.put(nextCallbackId, callback);
		return nextCallbackId++;
	}

	// Deregistriert den Callback mit der gegebenen ID, soweit vorhanden
	public void deregisterStateChangeCallback(int index) {
		stateChangeCallbacks.remove(index);
	}

	// Führt alle registrierten Callbacks aus
	private void propagateStateChange() {
		for(Runnable callback : stateChangeCallbacks.values()) {
			callback.run();
		}
	}

	// ---------------------------------------------------------------------------
	// SPIELBEGINN

	public Tisch(Spieler spieler1, Spieler spieler2, Spieler spieler3) {
		this.spieler1 = spieler1;
		this.spieler2 = spieler2;
		this.spieler3 = spieler3;
		spielNr = 1;
		reset();
	}

	public void reset() {
		//Leert alle Stapel
		skat.empty();
		stich.empty();
		spieler1.getHandCards().empty();
		spieler1.getWonCards().empty();
		spieler2.getHandCards().empty();
		spieler2.getWonCards().empty();
		spieler3.getHandCards().empty();
		spieler3.getWonCards().empty();
		// Verteilt Positionen
		if (spielNr % 3 == 0) {
			geben = spieler3;
			hoeren = spieler1;
			sagen = spieler2;
		}else if (spielNr % 3 == 1) {
			geben = spieler1;
			hoeren = spieler2;
			sagen = spieler3;			
		} else {
			geben = spieler2;
			hoeren = spieler3;
			sagen = spieler1;
		}
		// Initialisiert Zustand für die Reizphase
		reizindex = 0;
		reizwert = 0;
		hoerenGepasst = false;
		sagenGepasst = false;
		gebenGepasst = false;
		reizphaseVorbei = false;
		mitgegangen = true;
		ramsch = false;
		propagateStateChange();
	}
	
	public void deal() {
		Stapel deck = Stapel.newDeck();
		deck.moveN(hoeren.getHandCards(), 3);
		deck.moveN(sagen.getHandCards(), 3);
		deck.moveN(geben.getHandCards(), 3);
		deck.moveN(skat, 2);
		deck.moveN(hoeren.getHandCards(), 4);
		deck.moveN(sagen.getHandCards(), 4);
		deck.moveN(geben.getHandCards(), 4);
		deck.moveN(hoeren.getHandCards(), 3);
		deck.moveN(sagen.getHandCards(), 3);
		deck.moveN(geben.getHandCards(), 3);
		propagateStateChange();
	}

	// ---------------------------------------------------------------------------
	// REIZPHASE
	// Funktionen geben zurück, ob Aktion erfolgreich war (für UI Feedback).

	public Boolean weiterreizen(int spielerNr) {
		Spieler spieler = spieler(spielerNr);
		if (!mitgegangen
		|| (!(sagenGepasst && gebenGepasst) && spieler == hoeren)
		|| (!sagenGepasst && spieler == geben)
		|| (sagenGepasst && spieler == sagen)
		|| reizwert == 264
		) {
			return false;
		} else {
			reizwert = REIZWERTE[reizindex];
			reizindex++;
			solist = spieler;
			mitgegangen = false;
			if (spieler == hoeren) {
				reizphaseVorbei = true;
				ramsch = false;
			}
			propagateStateChange();
			return true;
		}
	}

	public Boolean mitgehen(int spielerNr) {
		Spieler spieler = spieler(spielerNr);
		if (mitgegangen
		|| spieler == geben
		|| (!hoerenGepasst && spieler == sagen)
		|| (hoerenGepasst && spieler == hoeren)
		) {
			return false;
		} else {
			mitgegangen = true;
			solist = spieler;
			propagateStateChange();
			return true;
		}
	}

	// Auch für Passen
	public Boolean aussteigen(int spielerNr) {
		Spieler spieler = spieler(spielerNr);
		if (mitgegangen) {
			if (spieler == hoeren
			|| (!(sagenGepasst && gebenGepasst) && spieler == hoeren)
			|| (!sagenGepasst && spieler == geben)
			|| (sagenGepasst && spieler == sagen)
			) {
				return false;
			} else {
				if (spieler == hoeren) {
					reizphaseVorbei = true;
					ramsch = true;
				} else if (spieler == sagen) {
					sagenGepasst = true;
				} else if (reizwert >= 18) {
					reizphaseVorbei = true;
					ramsch = false;
				} else {
					gebenGepasst = true;
				}
				propagateStateChange();
				return true;
			}
		} else {
			if (spieler == geben
			|| (!hoerenGepasst && spieler == sagen)
			|| (hoerenGepasst && spieler == hoeren)
			) {
				return false;
			} else {
				if (spieler == sagen || sagenGepasst) {
					reizphaseVorbei = true;
					ramsch = false;
				} else {
					hoerenGepasst = true;
				}
				propagateStateChange();
				return true;
			}
		}
	}

}
