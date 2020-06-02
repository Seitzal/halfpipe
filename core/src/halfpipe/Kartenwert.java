package halfpipe;

import java.util.NoSuchElementException;

public enum Kartenwert {
  SIEBEN("Sieben", 0, 7, 7),
  ACHT("Acht", 0, 8, 8),
  NEUN("Neun", 0, 9, 9),
  DAME("Dame", 0, 10, 12),
  KÖNIG("König", 0, 11, 13),
  ZEHN("Zehn", 0, 12, 10),
  ASS("Ass", 0, 13, 14),
  BUBE("Bube", 0, 14, 11);

  private Kartenwert(
      String name, 
      int value, 
      int strengthNormal, 
      int strengthNull) {
    this.name = name;
    this.value = value;
    this.strengthNormal = strengthNormal;
    this.strengthNull = strengthNull;
  }

  public final String name;
  public final int value;
  public final int strengthNormal;
  public final int strengthNull;

  public static Kartenwert fromName(String name) {
    switch (name) {
      case "Sieben": return SIEBEN;
      case "Acht": return ACHT;
      case "Neun": return NEUN;
      case "Dame": return DAME;
      case "König": return KÖNIG;
      case "Zehn": return ZEHN;
      case "Ass": return ASS;
      case "Bube": return BUBE;
      default: throw new NoSuchElementException("Ungültiger Kartenwert.");
    }
  }

}