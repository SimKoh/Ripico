package ripico.api.service;

import ripico.api.domain.Mitarbeiter;

public interface MitarbeiterService {
    Mitarbeiter login(String username, String password);
}
