package ripico.api.dal;

import ripico.api.domain.Wettschein;

import java.util.Optional;

public interface WettscheinAdapter {
    Optional<Wettschein> readWettschein(int wettenscheinId);
    int zaehleWettscheine();
}
