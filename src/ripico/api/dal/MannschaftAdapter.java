package ripico.api.dal;

import ripico.api.domain.Mannschaft;

import java.util.List;

//TODO Simon check, ob Adapter okay für dich ist
public interface MannschaftAdapter {
    List<Mannschaft> alleMannschaften();
}
