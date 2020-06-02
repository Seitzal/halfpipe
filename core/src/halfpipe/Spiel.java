package halfpipe;

public final class Spiel {

  public final int bubenFaktor; // 0 falls irrelevant (Nullspiel, Ramsch)
  public final Boolean istNull;
  public final Boolean istRamsch;
  public final Boolean istGrand;
  public final Boolean istFarbspiel;
  public final Farbe trumpffarbe; // null falls kein Farbspiel
  public final Boolean istHand;
  public Boolean istSchneiderAngesagt; // Kann nachgeholt werden, daher nicht final
  public Boolean istSchwarzAngesagt; // s.o.
  public final Boolean istOuvert;

  private int kontraStufe = 1; // 2 = Kontra, 4 = Re, 8 = Bock, 16 = Hirsch
  private int geschoben = 0;

  private Spiel(
      int bubenFaktor,
      Boolean istNull,
      Boolean istRamsch,
      Boolean istGrand,
      Boolean istFarbspiel,
      Farbe trumpffarbe,
      Boolean istHand,
      Boolean istSchneiderAngesagt,
      Boolean istSchwarzAngesagt,
      Boolean istOuvert) {
    this.bubenFaktor = bubenFaktor;
    this.istNull = istNull;
    this.istRamsch = istRamsch;
    this.istGrand = istGrand;
    this.istFarbspiel = istFarbspiel;
    this.trumpffarbe = trumpffarbe;
    this.istHand = istHand;
    this.istSchneiderAngesagt = istSchneiderAngesagt;
    this.istSchwarzAngesagt = istSchwarzAngesagt;
    this.istOuvert = istOuvert;
  }

  public static Spiel farbspiel(
      int bubenFaktor,
      Farbe trumpffarbe,
      Boolean istHand,
      Boolean istSchneiderAngesagt,
      Boolean istSchwarzAngesagt,
      Boolean istOuvert) {
    return new Spiel(
      bubenFaktor, 
      false, 
      false, 
      false, 
      false, 
      trumpffarbe, 
      istHand, 
      istSchneiderAngesagt, 
      istSchwarzAngesagt, 
      istOuvert
    );
  }

  public static Spiel nullspiel(boolean hand, boolean ouvert) {
    return new Spiel (
      0,
      true,
      false,
      false,
      false,
      null,
      hand,
      false,
      false,
      ouvert
    );
  }

  public static Spiel grand(
      int bubenFaktor,
      Boolean istHand,
      Boolean istSchneiderAngesagt,
      Boolean istSchwarzAngesagt,
      Boolean istOuvert) {
    return new Spiel(
      bubenFaktor, 
      false, 
      false, 
      true, 
      false, 
      null, 
      istHand, 
      istSchneiderAngesagt, 
      istSchwarzAngesagt, 
      istOuvert);
  }

  public static Spiel ramsch() {
    return new Spiel (
      0,
      false,
      false,
      false,
      false,
      null,
      false,
      false,
      false,
      false
    );
  }

}
