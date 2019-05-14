package ripico.dummy;

import ripico.api.dal.WettenAdapter;
import ripico.api.domain.Wette;

import java.util.List;
import java.util.Optional;

public class DummyWettenAdapter implements WettenAdapter {
    @Override
    public Wette createWette(Wette wette) {
        wette.setWettenId(4);
        return wette;
    }

    @Override
    public Optional<Wette> readWette(int wettenId) {
        return WettenMock.wettenListe.stream().filter(e -> e.getWettenId() == wettenId).findFirst();
    }

    @Override
    public Wette updateWette(Wette wette) {
        return wette;
    }

    @Override
    public boolean deleteWette(Wette wette) {
        return true;
    }

    @Override
    public List<Wette> getAllWetten() {
        return WettenMock.wettenListe;
    }
}
