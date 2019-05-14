package ripico.service;

import ripico.api.dal.WettenAdapter;
import ripico.api.domain.Wette;
import ripico.api.service.WettenService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultWettenServiceImpl implements WettenService {

    private final WettenAdapter wettenAdapter;

    public DefaultWettenServiceImpl(WettenAdapter wettenAdapter) {
        this.wettenAdapter = ServiceCreator.createWettenAdapter();
    }

    @Override
    public List<Wette> getOffeneWetten() {
        List<Wette> allWetten = wettenAdapter.getAllWetten();
        return allWetten.stream().filter(e -> e.getDatum().before(new Date())).collect(Collectors.toList());
    }
}
