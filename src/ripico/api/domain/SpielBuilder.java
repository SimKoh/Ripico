package ripico.api.domain;

import ripico.api.domain.enums.QuotenArt;
import ripico.api.domain.enums.Sportart;

import java.util.Date;
import java.util.Map;

public final class SpielBuilder {
    private int spielId;
    private Map<QuotenArt, Float> quoten;
    private Sportart sportart;
    private Mannschaft mannschaftHeim;
    private Mannschaft mannschaftAuswaerts;
    private Date datum;
    private QuotenArt ergebnis;

    private SpielBuilder() {
    }

    public static SpielBuilder newSpiel() {
        return new SpielBuilder();
    }

    public SpielBuilder withSpielId(int spielId) {
        this.spielId = spielId;
        return this;
    }

    public SpielBuilder withQuoten(Map<QuotenArt, Float> quoten) {
        this.quoten = quoten;
        return this;
    }

    public SpielBuilder withSportart(Sportart sportart) {
        this.sportart = sportart;
        return this;
    }

    public SpielBuilder withMannschaftHeim(Mannschaft mannschaftHeim) {
        this.mannschaftHeim = mannschaftHeim;
        return this;
    }

    public SpielBuilder withMannschaftAuswaerts(Mannschaft mannschaftAuswaerts) {
        this.mannschaftAuswaerts = mannschaftAuswaerts;
        return this;
    }

    public SpielBuilder withDatum(Date datum) {
        this.datum = datum;
        return this;
    }

    public SpielBuilder withErgebnis(QuotenArt ergebnis) {
        this.ergebnis = ergebnis;
        return this;
    }

    public Spiel build() {
        Spiel spiel = new Spiel();
        spiel.setSpielId(spielId);
        spiel.setQuoten(quoten);
        spiel.setSportart(sportart);
        spiel.setMannschaftHeim(mannschaftHeim);
        spiel.setMannschaftAuswaerts(mannschaftAuswaerts);
        spiel.setDatum(datum);
        spiel.setErgebnis(ergebnis);
        return spiel;
    }
}
