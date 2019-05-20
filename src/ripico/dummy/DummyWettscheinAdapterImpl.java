package ripico.dummy;

import ripico.api.dal.WettscheinAdapter;
import ripico.api.domain.Wettschein;
import ripico.api.domain.WettscheinBuilder;

import java.util.Optional;

public class DummyWettscheinAdapterImpl implements WettscheinAdapter {

    @Override
    public Optional<Wettschein> readWettschein(int wettenscheinId) {
        Wettschein wettschein = WettscheinBuilder
                .newWettschein()
                .withWettscheinId(1)
                .withWetten(WettenMock.wettenListe)
                .build();
        if (wettenscheinId == wettschein.getWettscheinId()) {
            return Optional.of(wettschein);
        }
        return Optional.empty();
    }

    @Override
    public int zaehleWettschein() {
        return 1;
    }
}
