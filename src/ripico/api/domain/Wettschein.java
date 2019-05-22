package ripico.api.domain;

import java.util.List;

public class Wettschein {
    private int wettscheinId;
    private List<Wette> wetten;
    private float einsatz;

    Wettschein() {

    }

    public int getWettscheinId() {
        return wettscheinId;
    }

    public void setWettscheinId(int wettscheinId) {
        this.wettscheinId = wettscheinId;
    }

    public List<Wette> getWetten() {
        return wetten;
    }

    public void setWetten(List<Wette> wetten) {
        this.wetten = wetten;
    }

    public float getEinsatz() {
        return einsatz;
    }

    public void setEinsatz(float einsatz) {
        this.einsatz = einsatz;
    }
}
