package ripico.api.domain;

import java.util.Date;

public class Wette {
    private int wettenId;
    private Spiel spiel;
    private QuotenArt gesetzteWette;



    public int getWettenId() {
        return wettenId;
    }

    public void setWettenId(int wettenId) {
        this.wettenId = wettenId;
    }

    public Spiel getSpiel() {
        return spiel;
    }

    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
    }

    public QuotenArt getGesetzteWette() {
        return gesetzteWette;
    }

    public void setGesetzteWette(QuotenArt gesetzteWette) {
        this.gesetzteWette = gesetzteWette;
    }
}
