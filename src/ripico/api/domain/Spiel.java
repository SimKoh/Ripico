package ripico.api.domain;

import ripico.api.domain.enums.QuotenArt;
import ripico.api.domain.enums.Sportart;

import java.util.*;

public class Spiel {

    private int spielId;
    private Map<QuotenArt, Float> quoten;
    private Sportart sportart;
    private Mannschaft mannschaftHeim;
    private Mannschaft mannschaftAuswaerts;
    private Date datum;
    private QuotenArt ergebnis;

    Spiel() {

    }

    public int getSpielId() {
        return spielId;
    }

    public void setSpielId(int spielId) {
        this.spielId = spielId;
    }

    public Map<QuotenArt, Float> getQuoten() {
        return quoten;
    }

    public void setQuoten(Map<QuotenArt, Float> quoten) {
        this.quoten = quoten;
    }

    public Sportart getSportart() {
        return sportart;
    }

    public void setSportart(Sportart sportart) {
        this.sportart = sportart;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public QuotenArt getErgebnis() {
        return ergebnis;
    }

    public void setErgebnis(QuotenArt ergebnis) {
        this.ergebnis = ergebnis;
    }


    public Mannschaft getMannschaftAuswaerts() {
        return mannschaftAuswaerts;
    }

    public void setMannschaftAuswaerts(Mannschaft mannschaftAuswaerts) {
        this.mannschaftAuswaerts = mannschaftAuswaerts;
    }

    public Mannschaft getMannschaftHeim() {
        return mannschaftHeim;
    }

    public void setMannschaftHeim(Mannschaft mannschaftHeim) {
        this.mannschaftHeim = mannschaftHeim;
    }

    public void setQuoten(float heim, float auswaerts, float unentschieden) {
        this.quoten = new EnumMap<>(QuotenArt.class);
        this.quoten.put(QuotenArt.HEIM, heim);
        this.quoten.put(QuotenArt.AUSWAERTS, auswaerts);
        this.quoten.put(QuotenArt.UNENTSCHIEDEN, unentschieden);

    }

    @Override
    public String toString() {
        return "ID " + getSpielId() + " - " + getMannschaftHeim() + " : " + getMannschaftAuswaerts();
    }
}
