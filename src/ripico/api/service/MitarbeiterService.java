package ripico.api.service;

import ripico.api.domain.Mitarbeiter;
import ripico.service.exception.InvalidCredentialsException;

public interface MitarbeiterService {
    Mitarbeiter login(String username, String password) throws InvalidCredentialsException;
    void logout();
}
