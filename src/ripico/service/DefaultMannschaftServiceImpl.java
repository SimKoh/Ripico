package ripico.service;

import ripico.api.ServiceFactory;
import ripico.api.dal.MannschaftAdapter;
import ripico.api.domain.Mannschaft;
import ripico.api.service.MannschaftService;

import java.util.List;

public class DefaultMannschaftServiceImpl implements MannschaftService {
    private MannschaftAdapter mannschaftAdapter;

    public DefaultMannschaftServiceImpl() {
        this.mannschaftAdapter = ServiceFactory.createService(MannschaftAdapter.class);
    }

    @Override
    public List<Mannschaft> alleMannschaften() {
        return mannschaftAdapter.alleMannschaften();
    }
}
