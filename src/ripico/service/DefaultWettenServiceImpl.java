package ripico.service;

import ripico.api.ServiceFactory;
import ripico.api.dal.WettenAdapter;
import ripico.api.domain.Wette;
import ripico.api.service.WettenService;

import java.util.ArrayList;
import java.util.List;

public class DefaultWettenServiceImpl implements WettenService {

    private final WettenAdapter wettenAdapter;

    public DefaultWettenServiceImpl() {
        this.wettenAdapter = ServiceFactory.createService(WettenAdapter.class);
    }

    @Override
    public List<Wette> getOffeneWetten() {
        List<Wette> allWetten = new ArrayList<>();
        return allWetten;
    }
}
