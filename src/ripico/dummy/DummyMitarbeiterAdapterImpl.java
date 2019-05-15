package ripico.dummy;

import ripico.api.dal.MitarbeiterAdapter;
import ripico.api.domain.Mitarbeiter;

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
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setBenutzername("admin");
        mitarbeiter.setPasswort("admin");
        mitarbeiter.setVorname("Rip");
        mitarbeiter.setNachname("Ico");
        return mitarbeiter;
    }
}
