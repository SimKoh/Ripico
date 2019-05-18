package ripico.dummy;

import ripico.api.dal.MitarbeiterAdapter;
import ripico.api.domain.Mitarbeiter;
import ripico.api.domain.MitarbeiterBuilder;

import java.util.Optional;

public class DummyMitarbeiterAdapterImpl implements MitarbeiterAdapter {
    @Override
    public Optional<Mitarbeiter> readMitarbeiter(String username) {
        Mitarbeiter mitarbeiter = createMitartbeiter();
        if (username.equals(mitarbeiter.getBenutzername())) {
            return Optional.of(mitarbeiter);
        }
        return Optional.empty();
    }

    private Mitarbeiter createMitartbeiter() {
        return MitarbeiterBuilder
                .newMitarbeiter()
                .withBenutzername("admin")
                .withPasswort("admin")
                .withVorname("Rip")
                .withNachname("Ico")
                .build();
    }
}
