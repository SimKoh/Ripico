package ripico.api.dal;

import ripico.api.domain.Mitarbeiter;
import ripico.service.exception.ResourceNotFoundException;

import java.sql.SQLException;
import java.util.Optional;

public interface MitarbeiterAdapter {
    Optional<Mitarbeiter> readMitarbeiter(String username);
}
