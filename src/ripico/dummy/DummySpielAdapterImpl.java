package ripico.dummy;

import ripico.api.dal.SpielAdapter;
import ripico.api.domain.Spiel;

import java.util.List;
import java.util.Optional;

public class DummySpielAdapterImpl implements SpielAdapter {
    @Override
    public Spiel createSpiel(Spiel spiel) {
        return spiel;
    }

    @Override
    public Optional<Spiel> readSpiel(int spielNr) {
        return WettenMock.spielListe.stream().filter(e-> e.getSpielId() == spielNr).findFirst();
    }

    @Override
    public Spiel updateSpiel(Spiel spiel) {
        return spiel;
    }

    @Override
    public List<Spiel> getAllSpiele() {
        return WettenMock.spielListe;
    }
}
