package ripico.api.dal;

import ripico.api.domain.Wette;

import java.util.List;
import java.util.Optional;

public interface WettenAdapter {
    Wette createWette(Wette wette);

    Optional<Wette> readWette(int wettenId);

    Wette updateWette(Wette wette);

    List<Wette> getAllWetten();

}
