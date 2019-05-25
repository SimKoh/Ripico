package ripico.service;

import ripico.api.ServiceFactory;
import ripico.api.dal.MitarbeiterAdapter;
import ripico.api.domain.Mitarbeiter;
import ripico.api.service.MitarbeiterService;
import ripico.api.exception.InvalidCredentialsException;
import ripico.service.session.SessionStorage;
import ripico.service.session.SessionTyp;

import java.util.Optional;

public class DefaultMitarbeiterServiceImpl implements MitarbeiterService {

    private MitarbeiterAdapter mitarbeiterAdapter;

    public DefaultMitarbeiterServiceImpl() {
        this.mitarbeiterAdapter = ServiceFactory.createService(MitarbeiterAdapter.class);
    }

    @Override
    public Mitarbeiter login(String username, String password) throws InvalidCredentialsException {
        Optional<Mitarbeiter> optionalMitarbeiter = mitarbeiterAdapter.readMitarbeiter(username);
        Mitarbeiter mitarbeiter = optionalMitarbeiter.orElseThrow(InvalidCredentialsException::new);
        boolean loginValid = false;
        if (mitarbeiter.getPasswort() != null) {
            loginValid = mitarbeiter.getPasswort().equals(password);
        }
        if (!loginValid) {
            throw new InvalidCredentialsException();
        } else {
            SessionStorage.getInstance().setSessionTyp(SessionTyp.ADMIN);
            return mitarbeiter;
        }
    }

    @Override
    public void logout() {
        SessionStorage.invalidateSession();
    }
}
