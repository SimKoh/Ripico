package ripico.service;

import ripico.api.dal.SpielAdapter;
import ripico.api.domain.QuotenArt;
import ripico.api.domain.Spiel;
import ripico.api.service.SpielService;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultSpielServiceImpl implements SpielService {
    private SpielAdapter spielAdapter;

    public DefaultSpielServiceImpl() {
        this.spielAdapter = ServiceCreator.createService(SpielAdapter.class);
    }

    @Override
    public List<Spiel> ladeSpiele() {
        return spielAdapter.getAllSpiele()
                .stream()
                .filter(e -> e.getDatum().after(new Date()))
                .collect(Collectors.toList());
    }

    @Override
    public void setzeErgebnis(Spiel spiel, QuotenArt ergebnis) {

    }
}