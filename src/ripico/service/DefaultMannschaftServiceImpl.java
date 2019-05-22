package ripico.service;

import ripico.api.ServiceFactory;
import ripico.api.dal.MannschaftAdapter;
import ripico.api.dal.MitarbeiterAdapter;
import ripico.api.domain.Mannschaft;
import ripico.api.service.MannschaftService;

import java.util.ArrayList;
import java.util.List;

//TODO Simon check, ob DefaultImpl okay für dich ist
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
