package ripico.dummy;

import ripico.api.dal.WettenAdapter;
import ripico.api.domain.Wette;

public class DummyWettenAdapterImpl implements WettenAdapter {
    @Override
    public Wette createWette(Wette wette) {
        wette.setWettenId(4);
        return wette;
    }
}
