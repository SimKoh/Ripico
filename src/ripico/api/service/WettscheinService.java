package ripico.api.service;

import ripico.api.domain.Wette;
import ripico.api.domain.Wettschein;
import ripico.service.exception.ResourceNotFoundException;

import java.util.List;

public interface WettscheinService {
    float berechneGesamtQuote(List<Wette> wetten);
    boolean pruefeWettschein(int wettscheinId) throws ResourceNotFoundException;
    Wettschein speichereWettschein(Wettschein wettschein);
    Wettschein erstelleLeerenWettschein();

}
