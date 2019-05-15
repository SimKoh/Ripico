package ripico.api.dal;

import ripico.api.domain.Wettschein;

import java.util.Optional;

public interface WettscheinAdapter {
    Wettschein createWettschein(Wettschein wettschein);

    Optional<Wettschein> readWettschein(int wettenscheinId);
}
