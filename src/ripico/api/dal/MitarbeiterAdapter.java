package ripico.api.dal;

import ripico.api.domain.Mitarbeiter;

import java.util.Optional;

public interface MitarbeiterAdapter {
    Optional<Mitarbeiter> readMitarbeiter(String username);
}
