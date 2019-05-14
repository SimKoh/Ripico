package ripico.api.service;

import ripico.api.domain.QuotenArt;
import ripico.api.domain.Spiel;
import sun.security.provider.ConfigFile;

public interface SpielService {
    Spiel ladeSpiel();
    void setzteErgebnis(Spiel spiel, QuotenArt ergebnis);
}
