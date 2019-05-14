package ripico.database;

import ripico.api.dal.WettenAdapter;
import ripico.api.domain.Wette;

import java.util.List;
import java.util.Optional;

public class DatabaseWettenAdapter implements WettenAdapter {

    @Override
    public Wette createWette(Wette wette) {
        return null;
    }

    @Override
    public Optional<Wette> readWette(int wettenId) {
        return null;
    }

    @Override
    public Wette updateWette(Wette wette) {
        return null;
    }

    @Override
    public boolean deleteWette(Wette wette) {
        return false;
    }

    @Override
    public List<Wette> getAllWetten() {
        return null;
    }
}
