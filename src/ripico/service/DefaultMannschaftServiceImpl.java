package ripico.service;

import ripico.api.dal.MannschaftAdapter;
import ripico.api.dal.MitarbeiterAdapter;
import ripico.api.domain.Mannschaft;
import ripico.api.service.MannschaftService;

import java.util.ArrayList;
import java.util.List;

public class DefaultMannschaftServiceImpl implements MannschaftService {
    private MannschaftAdapter mannschaftAdapter;

    @Override
    public List<Mannschaft> alleMannschaften() {
        return mannschaftAdapter.alleMannschaften();
    }
}
