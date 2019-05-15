package ripico.database;

import ripico.api.dal.MitarbeiterAdapter;
import ripico.api.domain.Mitarbeiter;

import java.util.Optional;

public class DatabaseMitarbeiterAdapterImpl implements MitarbeiterAdapter {
    @Override
    public Optional<Mitarbeiter> readMitarbeiter(String username) {
        return Optional.empty();
    }
}
