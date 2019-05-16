package ripico.api.dal;

import ripico.api.domain.Spiel;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface SpielAdapter {
    Spiel createSpiel(Spiel spiel) throws SQLException;

    Optional<Spiel> readSpiel(int spielNr);

    Spiel updateSpiel(Spiel spiel);

    List<Spiel> getAllSpiele();
}
