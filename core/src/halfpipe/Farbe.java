package halfpipe;

import java.util.NoSuchElementException;

public enum Farbe {
  KARO("Karo", 9),
  HERZ("Herz", 10),
  PIK("Pik", 11),
  KREUZ("Kreuz", 12),
  NULL("Null", 0);

  private Farbe(String name, int wert) {
    this.name = name;
    this.wert = wert;
  }

  public final String name;
  public final int wert;

  public static Farbe fromName(String name) {
    switch (name) {
      case "Karo": return KARO;
      case "Herz": return HERZ;
      case "Pik": return PIK;
      case "Kreuz": return KREUZ;
      case "Null": return NULL;
      default: throw new NoSuchElementException("Ungültige Farbe");
    }
  }

}