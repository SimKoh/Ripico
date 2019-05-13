package ripico.api.dal;

import ripico.api.domain.Mitarbeiter;

import java.util.Optional;

public interface MitarbeiterAdapter {
    Mitarbeiter createMitarbeiter(Mitarbeiter mitarbeiter);

    Optional<Mitarbeiter> readMitarbeiter(int mitarbeiterId);

    Mitarbeiter updateMitarbeiter(Mitarbeiter mitarbeiter);

    boolean deleteMitarbeiter(Mitarbeiter mitarbeiter);
}
