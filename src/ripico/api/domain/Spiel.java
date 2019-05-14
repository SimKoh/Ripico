package ripico.api.domain;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Spiel {

    private Map<QuotenArt,Float> quoten;
    private String sportart;
    private String mannschaftHeim;
    private String mannschaftAuswaerts;
    private Date datum;

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

    public void setQuoten(float heim, float auswaerts, float unentschieden) {
        this.quoten = new HashMap<>();
        this.quoten.put(QuotenArt.HEIM,heim);
        this.quoten.put(QuotenArt.AUSWAERTS,auswaerts);
        this.quoten.put(QuotenArt.UNENTSCHIEDEN,unentschieden);

    }
}
