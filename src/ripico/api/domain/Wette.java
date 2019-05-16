package ripico.api.domain;

public class Wette {
    private int wettenId;
    private int spielId;
    private int wettscheinId;
    private QuotenArt gesetzteWette;



    public int getWettenId() {
        return wettenId;
    }

    public void setWettenId(int wettenId) {
        this.wettenId = wettenId;
    }

    public int getSpielId() {
        return spielId;
    }

    public void setSpielId(int spielId) {
        this.spielId = spielId;
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
