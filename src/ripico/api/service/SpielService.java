package ripico.api.service;

import ripico.api.domain.QuotenArt;
import ripico.api.domain.Spiel;

import java.util.List;

public interface SpielService {
    List<Spiel> ladeSpiele();
    void setzteErgebnis(Spiel spiel, QuotenArt ergebnis);
}
