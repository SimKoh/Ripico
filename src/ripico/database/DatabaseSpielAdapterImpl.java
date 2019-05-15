package ripico.database;

import ripico.api.dal.SpielAdapter;
import ripico.api.domain.Spiel;

import java.util.List;
import java.util.Optional;

public class DatabaseSpielAdapterImpl implements SpielAdapter {
    @Override
    public Spiel createSpiel(Spiel spiel) {
        return null;
    }

    @Override
    public Optional<Spiel> readSpiel(int spielNr) {
        return Optional.empty();
    }

    @Override
    public Spiel updateSpiel(Spiel spiel) {
        return null;
    }

    @Override
    public List<Spiel> getAllSpiele() {
        return null;
    }
}
