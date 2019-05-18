package ripico.dummy;

import ripico.api.dal.WettscheinAdapter;
import ripico.api.domain.Wettschein;

import java.util.Optional;

public class DummyWettscheinAdapterImpl implements WettscheinAdapter {

    @Override
    public Optional<Wettschein> readWettschein(int wettenscheinId) {
        Wettschein wettschein = createWettschein();
        if (wettenscheinId == wettschein.getWettscheinId()) {
            return Optional.of(wettschein);
        }
        return Optional.empty();
    }

    private Wettschein createWettschein() {
        Wettschein wettschein = new Wettschein();
        wettschein.setWetten(WettenMock.wettenListe);
        wettschein.setWettscheinId(1);
        return wettschein;
    }
}
