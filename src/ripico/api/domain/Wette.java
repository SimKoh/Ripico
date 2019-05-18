package ripico.api.domain;

import ripico.api.domain.enums.QuotenArt;

public class Wette {
    private int wettenId;
    private Spiel spiel;
    private int wettscheinId;
    private QuotenArt gesetzteWette;

    Wette() {

    }

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

    public int getWettscheinId() {
        return wettscheinId;
    }

    public void setWettscheinId(int wettscheinId) {
        this.wettscheinId = wettscheinId;
    }

    public QuotenArt getGesetzteWette() {
        return gesetzteWette;
    }

    public void setGesetzteWette(QuotenArt gesetzteWette) {
        this.gesetzteWette = gesetzteWette;
    }
}
