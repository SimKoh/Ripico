package ripico.api.domain;

public final class WetteBuilder {
    private int wettenId;
    private int spielId;
    private int wettscheinId;
    private QuotenArt gesetzteWette;

    private WetteBuilder() {
    }

    public static WetteBuilder newWette() {
        return new WetteBuilder();
    }

    public WetteBuilder withWettenId(int wettenId) {
        this.wettenId = wettenId;
        return this;
    }

    public WetteBuilder withSpielId(int spielId) {
        this.spielId = spielId;
        return this;
    }

    public WetteBuilder withWettscheinId(int wettscheinId) {
        this.wettscheinId = wettscheinId;
        return this;
    }

    public WetteBuilder withGesetzteWette(QuotenArt gesetzteWette) {
        this.gesetzteWette = gesetzteWette;
        return this;
    }

    public Wette build() {
        Wette wette = new Wette();
        wette.setWettenId(wettenId);
        wette.setSpielId(spielId);
        wette.setWettscheinId(wettscheinId);
        wette.setGesetzteWette(gesetzteWette);
        return wette;
    }
}
