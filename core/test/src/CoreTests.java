import static org.junit.Assert.*;
import org.junit.*;

import halfpipe.*;
import static halfpipe.Farbe.*;
import static halfpipe.Kartenwert.*;

public class CoreTests {

  private boolean dummy = false;

  private Runnable dummyCallback = new Runnable() {
    public void run() {
      dummy = true;
    }
  };

  private boolean stateChanged() {
    if (dummy) {
      dummy = false;
      return true;
    } else return false;
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

  @Test
  public void stateCallbacks() {
    Tisch tisch = new Tisch(
      new Spieler("s1"), 
      new Spieler("s2"), 
      new Spieler("S3"));
    tisch.registerStateChangeCallback(dummyCallback);
    assertFalse(stateChanged());
    tisch.reset();
    assertTrue(stateChanged());
    assertFalse(stateChanged());
  }

  @Test
  // Eine relativ normale Reizphase. Alle Spieler versuchen, zu reizen.
  public void reizphase1() {
    Spieler s1 = new Spieler("s1");
    Spieler s2 = new Spieler("s2");
    Spieler s3 = new Spieler("s3");
    Tisch tisch = new Tisch(s1, s2, s3);
    tisch.registerStateChangeCallback(dummyCallback);
    // Reizphase Anfangszustand
    assertEquals(0, tisch.reizwert);
    assertEquals(s3, tisch.toAct);
    assertTrue(tisch.mitgegangen);
    assertFalse(stateChanged());
    // s3 sagt 18
    assertTrue(tisch.weiterreizen(3));
    assertTrue(stateChanged());
    assertEquals(18, tisch.reizwert);
    assertEquals(s3, tisch.solist);
    assertFalse(tisch.mitgegangen);
    assertEquals(s2, tisch.toAct);
    // s2 sagt ja
    assertTrue(tisch.mitgehen(2));
    assertTrue(stateChanged());
    assertEquals(18, tisch.reizwert);
    assertEquals(s2, tisch.solist);
    assertTrue(tisch.mitgegangen);
    assertEquals(s3, tisch.toAct);
    // s3 sagt 20
    assertTrue(tisch.weiterreizen(3));
    assertTrue(stateChanged());
    assertEquals(20, tisch.reizwert);
    assertEquals(s3, tisch.solist);
    assertFalse(tisch.mitgegangen);
    assertEquals(s2, tisch.toAct);
    // s2 sagt nein
    assertTrue(tisch.aussteigen(2));
    assertTrue(stateChanged());
    assertTrue(tisch.hoerenGepasst);
    assertTrue(tisch.mitgegangen);
    assertEquals(s1, tisch.toAct);
    // s1 sagt weiter 22
    assertTrue(tisch.weiterreizen(1));
    assertTrue(stateChanged());
    assertEquals(22, tisch.reizwert);
    assertEquals(s1, tisch.solist);
    assertFalse(tisch.mitgegangen);
    assertEquals(s3, tisch.toAct);
    // s3 sagt ja
    assertTrue(tisch.mitgehen(3));
    assertTrue(stateChanged());
    assertEquals(s3, tisch.solist);
    assertTrue(tisch.mitgegangen);
    assertEquals(s1, tisch.toAct);
    // s1 sagt 23
    assertTrue(tisch.weiterreizen(1));
    assertTrue(stateChanged());
    assertEquals(23, tisch.reizwert);
    assertEquals(s1, tisch.solist);
    assertFalse(tisch.mitgegangen);
    assertEquals(s3, tisch.toAct);
    // s3 sagt nein
    assertTrue(tisch.aussteigen(3));
    assertTrue(stateChanged());
    // s1 spielt allein
    assertTrue(tisch.reizphaseVorbei);
    assertFalse(tisch.ramsch);
    assertEquals(s1, tisch.toAct);
  }

  @Test
  // Eine kurze Reizphase. Sager und Geber passen, Hörer nimmt das Spiel für 18.
  public void reizphase2() {
    Spieler s1 = new Spieler("s1");
    Spieler s2 = new Spieler("s2");
    Spieler s3 = new Spieler("s3");
    Tisch tisch = new Tisch(s1, s2, s3);
    tisch.registerStateChangeCallback(dummyCallback);
    // Reizphase Anfangszustand
    assertEquals(0, tisch.reizwert);
    assertEquals(s3, tisch.toAct);
    assertTrue(tisch.mitgegangen);
    assertFalse(stateChanged());
    // s3 passt
    assertTrue(tisch.aussteigen(3));
    assertTrue(stateChanged());
    assertEquals(0, tisch.reizwert);
    assertTrue(tisch.sagenGepasst);
    assertTrue(tisch.mitgegangen);
    assertEquals(s1, tisch.toAct);
    // s1 passt
    assertTrue(tisch.aussteigen(1));
    assertTrue(stateChanged());
    assertEquals(0, tisch.reizwert);
    assertTrue(tisch.gebenGepasst);
    assertTrue(tisch.mitgegangen);
    assertEquals(s2, tisch.toAct);
    // s2 sagt 18 und spielt allein
    assertTrue(tisch.weiterreizen(2));
    assertTrue(stateChanged());
    assertEquals(18, tisch.reizwert);
    assertTrue(tisch.reizphaseVorbei);
    assertFalse(tisch.ramsch);
    assertEquals(s2, tisch.toAct);
  }

  @Test
  // Eine kurze Reizphase. Alle passen und es kommt zum Ramsch.
  public void reizphase3() {
    Spieler s1 = new Spieler("s1");
    Spieler s2 = new Spieler("s2");
    Spieler s3 = new Spieler("s3");
    Tisch tisch = new Tisch(s1, s2, s3);
    tisch.registerStateChangeCallback(dummyCallback);
    // Reizphase Anfangszustand
    assertEquals(0, tisch.reizwert);
    assertEquals(s3, tisch.toAct);
    assertTrue(tisch.mitgegangen);
    assertFalse(stateChanged());
    // s3 passt
    assertTrue(tisch.aussteigen(3));
    assertTrue(stateChanged());
    assertEquals(0, tisch.reizwert);
    assertTrue(tisch.sagenGepasst);
    assertTrue(tisch.mitgegangen);
    assertEquals(s1, tisch.toAct);
    // s1 passt
    assertTrue(tisch.aussteigen(1));
    assertTrue(stateChanged());
    assertEquals(0, tisch.reizwert);
    assertTrue(tisch.gebenGepasst);
    assertTrue(tisch.mitgegangen);
    assertEquals(s2, tisch.toAct);
    // s2 passt
    assertTrue(tisch.aussteigen(2));
    assertTrue(stateChanged());
    // Es wird Ramsch gespielt
    assertTrue(tisch.reizphaseVorbei);
    assertTrue(tisch.ramsch);
    // TODO: Assertion for toAct
  }
  

}