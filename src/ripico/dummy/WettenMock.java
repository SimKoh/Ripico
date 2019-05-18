package ripico.dummy;

import ripico.api.domain.enums.QuotenArt;
import ripico.api.domain.Spiel;
import ripico.api.domain.Wette;
import ripico.api.domain.WetteBuilder;

import java.util.*;

public class WettenMock {

    public static final Wette wette1 = createWette1();
    public static final Wette wette2 = createWette2();
    public static final Wette wette3 = createWette3();

    public static final Spiel spiel1 = createSpiel1();
    public static final Spiel spiel2 = createSpiel2();
    public static final Spiel spiel3 = createSpiel3();

    public static final List<Wette> wettenListe = new ArrayList<>();
    public static final List<Spiel> spielListe = new ArrayList<>();

    static {
        wettenListe.add(wette1);
        wettenListe.add(wette2);
        wettenListe.add(wette3);

        spielListe.add(spiel1);
        spielListe.add(spiel2);
        spielListe.add(spiel3);
    }

    public static Wette createWette1() {
        return WetteBuilder.newWette()
                .withWettenId(1)
                .withSpiel(createSpiel1())
                .withWettscheinId(1)
                .withGesetzteWette(QuotenArt.AUSWAERTS)
                .build();
    }

    public static Wette createWette2() {
        return WetteBuilder.newWette()
                .withWettenId(2)
                .withSpiel(createSpiel2())
                .withWettscheinId(1)
                .withGesetzteWette(QuotenArt.HEIM)
                .build();
    }

    public static Wette createWette3() {
        return WetteBuilder.newWette()
                .withWettenId(3)
                .withSpiel(createSpiel3())
                .withWettscheinId(2)
                .withGesetzteWette(QuotenArt.UNENTSCHIEDEN)
                .build();
    }

    public static Spiel createSpiel1() {
        Spiel spiel = new Spiel();
        spiel.setDatum(new GregorianCalendar(2019, Calendar.MAY, 13).getTime());
        spiel.setSportart("Fußball");
        spiel.setMannschaftHeim("BVB");
        spiel.setMannschaftHeimLogoPfad("/imgs/logos/Fußball/bvb.gif");
        spiel.setMannschaftAuswaerts("SO4");
        spiel.setMannschaftAuswaertsLogoPfad("/imgs/logos/Fußball/bvb.gif"); // TODO Schalke Logo einfügen
        spiel.setQuoten(3.02f, 1.89f, 2.57f);
        return spiel;
    }

    public static Spiel createSpiel2() {
        Spiel spiel = new Spiel();
        spiel.setDatum(new GregorianCalendar(2019, Calendar.MAY, 17).getTime());
        spiel.setSportart("Eishockey");
        spiel.setMannschaftHeim("Kölner Haie");
        spiel.setMannschaftHeimLogoPfad("/resources/imgs/logos/Eishockey/koelnerHaie.gif");
        spiel.setMannschaftAuswaerts("Düsseldorfer EG");
        spiel.setMannschaftAuswaertsLogoPfad("/resources/imgs/logos/Eishockey/koelnerHaie.gif"); // TODO duesseldorferEg Logo einfügen
        spiel.setQuoten(1.01f, 10.70f, 9.53f);
        return spiel;
    }

    public static Spiel createSpiel3() {
        Spiel spiel = new Spiel();
        spiel.setDatum(new GregorianCalendar(2019, Calendar.MAY, 19).getTime());
        spiel.setSportart("Basketball");
        spiel.setMannschaftHeim("Warriors");
        spiel.setMannschaftAuswaerts("Rockets");
        spiel.setQuoten(1.22f, 5.89f, 7.57f);
        return spiel;
    }

    public static Spiel createSpiel4() {
        Spiel spiel = new Spiel();
        spiel.setDatum(new GregorianCalendar(2019, Calendar.MAY, 21).getTime());
        spiel.setSportart("Basketball");
        spiel.setMannschaftHeim("Chicago Bulls");
        spiel.setMannschaftHeimLogoPfad("/resources/imgs/logos/Basketball/chicagoBulls.png");
        spiel.setMannschaftAuswaerts("Rockets");
        spiel.setMannschaftAuswaertsLogoPfad("/resources/imgs/logos/Basketball/chicagoBulls.png"); // TODO rockets Logo einfügen
        spiel.setQuoten(1.22f, 5.89f, 7.57f);
        return spiel;
    }
}
