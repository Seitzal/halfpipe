package halfpipe;

import java.util.NoSuchElementException;

public enum Farbe {
  KARO("Karo", 9),
  HERZ("Herz", 10),
  PIK("Pik", 11),
  KREUZ("Kreuz", 12);

  private Farbe(String name, int value) {
    this.name = name;
    this.value = value;
  }

  public final String name;
  public final int value;

  public static Farbe fromName(String name) {
    switch (name) {
      case "Karo": return KARO;
      case "Herz": return HERZ;
      case "Pik": return PIK;
      case "Kreuz": return KREUZ;
      default: throw new NoSuchElementException("Ungültige Farbe");
    }
  }

}