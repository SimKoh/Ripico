package ripico.service;

import ripico.api.ServiceFactory;
import ripico.api.dal.WettenAdapter;
import ripico.api.dal.WettscheinAdapter;
import ripico.api.domain.WettscheinBuilder;
import ripico.api.domain.enums.QuotenArt;
import ripico.api.domain.Wette;
import ripico.api.domain.Wettschein;
import ripico.api.service.WettscheinService;
import ripico.service.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultWettscheinServiceImpl implements WettscheinService {

    private WettscheinAdapter wettscheinAdapter;
    private WettenAdapter wettenAdapter;

    public DefaultWettscheinServiceImpl() {
        this.wettscheinAdapter = ServiceFactory.createService(WettscheinAdapter.class);
        this.wettenAdapter = ServiceFactory.createService(WettenAdapter.class);
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
            List<Wette> wettenReturn = new ArrayList<>();
            for (Wette wette : wettschein.getWetten()) {
                if (wette.getGesetzteWette() == null) {
                    throw new IllegalArgumentException();
                } else {
                    wettenReturn.add(wettenAdapter.createWette(wette));
                }
            }
            wettschein.setWetten(wettenReturn);
        }
        return wettschein;
    }

    @Override
    public Wettschein erstelleLeerenWettschein() {
        return WettscheinBuilder
                .newWettschein()
                .withWettscheinId(wettscheinAdapter.zaehleWettscheine() + 1)
                .withWetten(new ArrayList<>())
                .build();
    }

    private float getQuoteFromWette(Wette wette) {
        QuotenArt gesetzteWette = wette.getGesetzteWette();
        return wette.getSpiel().getQuoten().get(gesetzteWette);
    }

}
