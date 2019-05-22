package ripico.api.domain;

import java.util.List;

public final class WettscheinBuilder {
    private int wettscheinId;
    private List<Wette> wetten;
    private float einsatz;

    private WettscheinBuilder() {
    }

    public static WettscheinBuilder newWettschein() {
        return new WettscheinBuilder();
    }

    public WettscheinBuilder withWettscheinId(int wettscheinId) {
        this.wettscheinId = wettscheinId;
        return this;
    }

    public WettscheinBuilder withWetten(List<Wette> wetten) {
        this.wetten = wetten;
        return this;
    }

    public WettscheinBuilder withEinsatz(float einsatz) {
        this.einsatz = einsatz;
        return this;
    }

    public Wettschein build() {
        Wettschein wettschein = new Wettschein();
        wettschein.setWettscheinId(wettscheinId);
        wettschein.setWetten(wetten);
        wettschein.setEinsatz(einsatz);
        return wettschein;
    }
}
