package ripico.dummy;

import ripico.api.domain.QuotenArt;
import ripico.api.domain.Spiel;
import ripico.api.domain.Wette;

import java.util.*;

public class WettenMock {

    public static final Wette wette1 = createWette1();
    public static final Wette wette2 = createWette2();
    public static final Wette wette3 = createWette3();

    public static final List<Wette> wettenListe = new ArrayList<>();

    static {
        wettenListe.add(wette1);
        wettenListe.add(wette2);
        wettenListe.add(wette3);
    }

    public static Wette createWette1() {
        Wette wette = new Wette();
        Spiel spiel = new Spiel();
        wette.setWettenId(1);
        spiel.setDatum(new GregorianCalendar(2019, Calendar.MAY, 13).getTime());
        spiel.setSportart("Fußball");
        spiel.setMannschaftHeim("BVB");
        spiel.setMannschaftAuswaerts("SO4");
        spiel.setQuoten(3.02f, 1.89f, 2.57f);
        wette.setGesetzteWette(QuotenArt.AUSWAERTS);
        return wette;
    }

    public static Wette createWette2() {
        Wette wette = new Wette();
        Spiel spiel = new Spiel();
        wette.setWettenId(2);
        spiel.setDatum(new GregorianCalendar(2019, Calendar.MAY, 13).getTime());
        spiel.setSportart("Eishockey");
        spiel.setMannschaftHeim("Kölner Haie");
        spiel.setMannschaftAuswaerts("Düsseldorfer EG");
        spiel.setQuoten(1.01f, 10.70f, 9.53f);
        wette.setSpiel(spiel);
        wette.setGesetzteWette(QuotenArt.HEIM);
        return wette;
    }

    public static Wette createWette3() {
        Wette wette = new Wette();
        Spiel spiel = new Spiel();
        wette.setWettenId(3);
        spiel.setDatum(new GregorianCalendar(2019, Calendar.MAY, 13).getTime());
        spiel.setSportart("Basketball");
        spiel.setMannschaftHeim("Warriors");
        spiel.setMannschaftAuswaerts("Rockets");
        spiel.setQuoten(1.22f, 5.89f, 7.57f);
        wette.setSpiel(spiel);
        wette.setGesetzteWette(QuotenArt.UNENTSCHIEDEN);
        return wette;
    }
}
