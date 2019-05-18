package ripico.api.domain;

import ripico.api.domain.enums.QuotenArt;

import java.util.*;

public class Spiel {

    private int spielId;
    private Map<QuotenArt, Float> quoten;
    private String sportart;
    private String mannschaftHeim;
    private String mannschaftHeimLogoPfad;
    private String mannschaftAuswaerts;
    private String mannschaftAuswaertsLogoPfad;
    private Date datum;
    private QuotenArt ergebnis;

    //TODO make packge private -> fix dummy
    public Spiel() {

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

    public String getSportart() {
        return sportart;
    }

    public void setSportart(String sportart) {
        this.sportart = sportart;
    }

    public String getMannschaftHeim() {
        return mannschaftHeim;
    }

    public void setMannschaftHeim(String mannschaftHeim) {
        this.mannschaftHeim = mannschaftHeim;
    }

    public String getMannschaftAuswaerts() {
        return mannschaftAuswaerts;
    }

    public void setMannschaftAuswaerts(String mannschaftAuswaerts) {
        this.mannschaftAuswaerts = mannschaftAuswaerts;
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

    public String getMannschaftHeimLogoPfad() {
        return mannschaftHeimLogoPfad;
    }

    public void setMannschaftHeimLogoPfad(String mannschaftHeimLogoPfad) {
        this.mannschaftHeimLogoPfad = mannschaftHeimLogoPfad;
    }

    public String getMannschaftAuswaertsLogoPfad() {
        return mannschaftAuswaertsLogoPfad;
    }

    public void setMannschaftAuswaertsLogoPfad(String mannschaftAuswaertsLogoPfad) {
        this.mannschaftAuswaertsLogoPfad = mannschaftAuswaertsLogoPfad;
    }

    public void setQuoten(float heim, float auswaerts, float unentschieden) {
        this.quoten = new EnumMap<>(QuotenArt.class);
        this.quoten.put(QuotenArt.HEIM, heim);
        this.quoten.put(QuotenArt.AUSWAERTS, auswaerts);
        this.quoten.put(QuotenArt.UNENTSCHIEDEN, unentschieden);

    }
}
