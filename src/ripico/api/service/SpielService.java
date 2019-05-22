package ripico.api.service;

import ripico.api.domain.Spiel;
import ripico.api.domain.enums.QuotenArt;

import java.util.List;

public interface SpielService {
    List<Spiel> ladeSpiele();

    void setzeErgebnis(Spiel spiel, QuotenArt ergebnis);

    void erstelleSpiel(Spiel spiel);
}
