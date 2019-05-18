package ripico.api.domain;

import ripico.api.domain.enums.QuotenArt;

public final class WetteBuilder {
    private int wettenId;
    private Spiel spiel;
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

    public WetteBuilder withSpiel(Spiel spiel) {
        this.spiel = spiel;
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
        wette.setSpiel(spiel);
        wette.setWettscheinId(wettscheinId);
        wette.setGesetzteWette(gesetzteWette);
        return wette;
    }
}
