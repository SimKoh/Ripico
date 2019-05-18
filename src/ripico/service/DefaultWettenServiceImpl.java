package ripico.service;

import ripico.api.ServiceFactory;
import ripico.api.dal.WettenAdapter;
import ripico.api.domain.Wette;
import ripico.api.service.WettenService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
