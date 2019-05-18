package ripico.service;

import ripico.api.ServiceFactory;
import ripico.api.dal.WettscheinAdapter;
import ripico.api.domain.enums.QuotenArt;
import ripico.api.domain.Wette;
import ripico.api.domain.Wettschein;
import ripico.api.service.WettscheinService;
import ripico.service.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

public class DefaultWettscheinServiceImpl implements WettscheinService {

    private WettscheinAdapter wettscheinAdapter;

    public DefaultWettscheinServiceImpl() {
        this.wettscheinAdapter = ServiceFactory.createService(WettscheinAdapter.class);
    }

    @Override
    public float berechneGesamtQuote(List<Wette> wetten) {
        float gesamtQuote = getQuoteFromWette(wetten.get(0));
        for (int i = 1; i < wetten.size(); i++) {
            gesamtQuote *= getQuoteFromWette(wetten.get(i));
        }
        return gesamtQuote;
    }

    @Override
    public boolean pruefeWettschein(int wettscheinId) throws ResourceNotFoundException {
        Optional<Wettschein> optionalWettschein = wettscheinAdapter.readWettschein(wettscheinId);
        Wettschein wettschein = optionalWettschein.orElseThrow(ResourceNotFoundException::new);
        for (Wette wette : wettschein.getWetten()) {
            if (!wette.getGesetzteWette().equals(wette.getSpiel().getErgebnis())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Wettschein speichereWettschein(Wettschein wettschein) {
        if (wettschein.getWetten() == null) {
            throw new IllegalArgumentException();
        } else {
            for (Wette wette : wettschein.getWetten()) {
                if (wette.getGesetzteWette() == null) {
                    throw new IllegalArgumentException();
                }
            }
        }
        return wettscheinAdapter.createWettschein(wettschein);
    }

    private float getQuoteFromWette(Wette wette) {
        QuotenArt gesetzteWette = wette.getGesetzteWette();
        return wette.getSpiel().getQuoten().get(gesetzteWette);
    }

}
