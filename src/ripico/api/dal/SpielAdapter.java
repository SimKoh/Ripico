package ripico.api.dal;

import ripico.api.domain.Spiel;

import java.util.Optional;

public interface SpielAdapter {
    Spiel createSpiel(Spiel spiel);

    Optional<Spiel> readSpiel(int spielNr);

    Spiel updateSpiel(Spiel spiel);

    boolean deleteSpiel(int spielId);
}
