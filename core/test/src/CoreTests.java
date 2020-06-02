import static org.junit.Assert.*;
import org.junit.*;

import halfpipe.*;
import static halfpipe.Farbe.*;
import static halfpipe.Kartenwert.*;

public class CoreTests {

  @Test
  public void exampleTest() {
    assertTrue(true);
  }

  @Test
  public void trumpfErkennung() {
    Spiel spiel = Spiel.farbspiel(2, HERZ, false, false, false, false);
    assertFalse(new Karte(KARO, DAME).istTrumpf(spiel));
    assertTrue(new Karte(HERZ, ZEHN).istTrumpf(spiel));
    assertFalse(new Karte(PIK, ASS).istTrumpf(spiel));
    assertFalse(new Karte(KREUZ, SIEBEN).istTrumpf(spiel));
    assertTrue(new Karte(PIK, BUBE).istTrumpf(spiel));
    spiel = Spiel.grand(4, false, false, false, false);
    assertFalse(new Karte(KARO, DAME).istTrumpf(spiel));
    assertFalse(new Karte(HERZ, ZEHN).istTrumpf(spiel));
    assertFalse(new Karte(PIK, ASS).istTrumpf(spiel));
    assertFalse(new Karte(KREUZ, SIEBEN).istTrumpf(spiel));
    assertTrue(new Karte(PIK, BUBE).istTrumpf(spiel));
    spiel = Spiel.nullspiel(false, false);
    assertFalse(new Karte(KARO, DAME).istTrumpf(spiel));
    assertFalse(new Karte(HERZ, ZEHN).istTrumpf(spiel));
    assertFalse(new Karte(PIK, ASS).istTrumpf(spiel));
    assertFalse(new Karte(KREUZ, SIEBEN).istTrumpf(spiel));
    assertFalse(new Karte(KREUZ, BUBE).istTrumpf(spiel));
  }

}