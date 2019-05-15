package ripico.database;

import ripico.api.dal.WettscheinAdapter;
import ripico.api.domain.Wettschein;

import java.util.Optional;

public class DatabaseWettscheinAdapterImpl implements WettscheinAdapter {
    @Override
    public Wettschein createWettschein(Wettschein wettschein) {
        return null;
    }

    @Override
    public Optional<Wettschein> readWettschein(int wettenscheinId) {
        return Optional.empty();
    }
}
