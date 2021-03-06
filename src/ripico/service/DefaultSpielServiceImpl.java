package ripico.service;

import ripico.api.ServiceFactory;
import ripico.api.dal.SpielAdapter;
import ripico.api.domain.Spiel;
import ripico.api.domain.enums.QuotenArt;
import ripico.api.service.SpielService;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultSpielServiceImpl implements SpielService {
    private SpielAdapter spielAdapter;

    public DefaultSpielServiceImpl() {
        this.spielAdapter = ServiceFactory.createService(SpielAdapter.class);
    }

    @Override
    public List<Spiel> ladeSpiele() {
        return spielAdapter.getAllSpiele()
                .stream()
                .filter(e -> e.getDatum().after(new Date()) && e.getErgebnis() == null)
                .collect(Collectors.toList());
    }

    @Override
    public void setzeErgebnis(Spiel spiel, QuotenArt ergebnis) {
        spiel.setErgebnis(ergebnis);
        spielAdapter.updateSpiel(spiel);
    }

    @Override
    public void erstelleSpiel(Spiel spiel) {
        try {
            spielAdapter.createSpiel(spiel);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
