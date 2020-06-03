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

  public int kontraStufe; // 1 = Normal, 2 = Kontra, 4 = Re, 8 = Bock, 16 = Hirsch
  public int geschoben;

  public Spiel(
      int bubenFaktor,
      Boolean istNull,
      Boolean istRamsch,
      Boolean istGrand,
      Boolean istFarbspiel,
      Farbe trumpffarbe,
      Boolean istHand,
      Boolean istSchneiderAngesagt,
      Boolean istSchwarzAngesagt,
      Boolean istOuvert,
      int kontraStufe,
      int geschoben) {
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
    this.kontraStufe = kontraStufe;
    this.geschoben = geschoben;
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
      true, 
      trumpffarbe, 
      istHand, 
      istSchneiderAngesagt, 
      istSchwarzAngesagt, 
      istOuvert,
      1,
      0
    );
  }

  public static Spiel nullspiel(boolean hand, boolean ouvert) {
    return new Spiel (
      0,
      true,
      false,
      false,
      false,
      Farbe.NULL,
      hand,
      false,
      false,
      ouvert,
      1,
      0
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
      Farbe.NULL, 
      istHand, 
      istSchneiderAngesagt, 
      istSchwarzAngesagt, 
      istOuvert,
      1,
      0);
  }

  public static Spiel ramsch(int geschoben) {
    return new Spiel (
      0,
      false,
      false,
      false,
      false,
      Farbe.NULL,
      false,
      false,
      false,
      false,
      1,
      geschoben
    );
  }

  @Override public boolean equals(Object o) {
    if (!(o instanceof Spiel))
      return false;
    Spiel anderes = (Spiel) o;
    return 
      this.bubenFaktor == anderes.bubenFaktor &&
      this.istNull == anderes.istNull &&
      this.istRamsch == anderes.istRamsch &&
      this.istGrand == anderes.istGrand &&
      this.istFarbspiel == anderes.istFarbspiel &&
      this.trumpffarbe == anderes.trumpffarbe &&
      this.istHand == anderes.istHand &&
      this.istSchneiderAngesagt == anderes.istSchneiderAngesagt &&
      this.istSchwarzAngesagt == anderes.istSchwarzAngesagt &&
      this.istOuvert == anderes.istOuvert &&
      this.kontraStufe == anderes.kontraStufe &&
      this.geschoben == anderes.geschoben;
  }

}
