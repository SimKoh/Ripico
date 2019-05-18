package ripico.api.domain;

import ripico.api.domain.enums.QuotenArt;

import java.util.Date;
import java.util.Map;

public final class SpielBuilder {
    private int spielId;
    private Map<QuotenArt, Float> quoten;
    private String sportart;
    private String mannschaftHeim;
    private String mannschaftHeimLogoPfad;
    private String mannschaftAuswaerts;
    private String mannschaftAuswaertsLogoPfad;
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

    public SpielBuilder withSportart(String sportart) {
        this.sportart = sportart;
        return this;
    }

    public SpielBuilder withMannschaftHeim(String mannschaftHeim) {
        this.mannschaftHeim = mannschaftHeim;
        return this;
    }

    public SpielBuilder withMannschaftHeimLogoPfad(String mannschaftHeimLogoPfad) {
        this.mannschaftHeimLogoPfad = mannschaftHeimLogoPfad;
        return this;
    }

    public SpielBuilder withMannschaftAuswaerts(String mannschaftAuswaerts) {
        this.mannschaftAuswaerts = mannschaftAuswaerts;
        return this;
    }

    public SpielBuilder withMannschaftAuswaertsLogoPfad(String mannschaftAuswaertsLogoPfad) {
        this.mannschaftAuswaertsLogoPfad = mannschaftAuswaertsLogoPfad;
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
        spiel.setMannschaftHeimLogoPfad(mannschaftHeimLogoPfad);
        spiel.setMannschaftAuswaerts(mannschaftAuswaerts);
        spiel.setMannschaftAuswaertsLogoPfad(mannschaftAuswaertsLogoPfad);
        spiel.setDatum(datum);
        spiel.setErgebnis(ergebnis);
        return spiel;
    }
}
