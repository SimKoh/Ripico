package ripico.dummy;

import ripico.api.domain.*;
import ripico.api.domain.enums.QuotenArt;
import ripico.api.domain.enums.Sportart;

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
        Map<QuotenArt, Float> quoten = new EnumMap<>(QuotenArt.class);
        quoten.put(QuotenArt.HEIM, 3.02f);
        quoten.put(QuotenArt.UNENTSCHIEDEN, 2.57f);
        quoten.put(QuotenArt.AUSWAERTS, 1.87f);
        return SpielBuilder
                .newSpiel()
                .withDatum(new GregorianCalendar(2019, Calendar.MAY, 27).getTime())
                .withSportart(Sportart.Fussball)
                .withMannschaftHeim(MannschaftBuilder.newMannschaft().withMannschaftsName("BVB").withMannschaftLogo("/resources/imgs/logos/Fußball/bvb.png").build())
                .withMannschaftAuswaerts(MannschaftBuilder.newMannschaft().withMannschaftsName("Barcelona").withMannschaftLogo("/resources/imgs/logos/Fußball/barcelona.png").build())
                .withQuoten(quoten)
                .build();
    }

    public static Spiel createSpiel2() {
        Map<QuotenArt, Float> quoten = new EnumMap<>(QuotenArt.class);
        quoten.put(QuotenArt.HEIM, 1.01f);
        quoten.put(QuotenArt.UNENTSCHIEDEN, 10.7f);
        quoten.put(QuotenArt.AUSWAERTS, 9.57f);
        return SpielBuilder
                .newSpiel()
                .withDatum(new GregorianCalendar(2019, Calendar.MAY, 21).getTime())
                .withSportart(Sportart.Eishockey)
                .withMannschaftHeim(MannschaftBuilder.newMannschaft().withMannschaftsName("Kölner Haie").withMannschaftLogo("/resources/imgs/logos/Eishockey/koelnerHaie.png").build())
                .withMannschaftAuswaerts(MannschaftBuilder.newMannschaft().withMannschaftsName("Düsseldorfer EG").withMannschaftLogo("/resources/imgs/logos/Eishockey/duesseldorferEG.png").build())
                .withQuoten(quoten)
                .build();
    }

    public static Spiel createSpiel3() {
        Map<QuotenArt, Float> quoten = new EnumMap<>(QuotenArt.class);
        quoten.put(QuotenArt.HEIM, 1.45f);
        quoten.put(QuotenArt.UNENTSCHIEDEN, 2.21f);
        quoten.put(QuotenArt.AUSWAERTS, 4.19f);
        return SpielBuilder
                .newSpiel()
                .withDatum(new GregorianCalendar(2019, Calendar.MAY, 19).getTime())
                .withSportart(Sportart.Basketball)
                .withMannschaftHeim(MannschaftBuilder.newMannschaft().withMannschaftsName("Boston Celtics").withMannschaftLogo("/resources/imgs/logos/Basketball/bostonCeltics.png").build())
                .withMannschaftAuswaerts(MannschaftBuilder.newMannschaft().withMannschaftsName("Miami Heat").withMannschaftLogo("/resources/imgs/logos/Basketball/miamiHeat.png").build())
                .withQuoten(quoten)
                .build();
    }

    public static Spiel createSpiel4() {
        Map<QuotenArt, Float> quoten = new EnumMap<>(QuotenArt.class);
        quoten.put(QuotenArt.HEIM, 1.22f);
        quoten.put(QuotenArt.UNENTSCHIEDEN, 5.98f);
        quoten.put(QuotenArt.AUSWAERTS, 7.57f);
        return SpielBuilder
                .newSpiel()
                .withDatum(new GregorianCalendar(2019, Calendar.MAY, 21).getTime())
                .withSportart(Sportart.Basketball)
                .withMannschaftHeim(MannschaftBuilder.newMannschaft().withMannschaftsName("Chicago Bulls").withMannschaftLogo("/resources/imgs/logos/Basketball/chicagoBulls.png").build())
                .withMannschaftAuswaerts(MannschaftBuilder.newMannschaft().withMannschaftsName("LA Lakers").withMannschaftLogo("/resources/imgs/logos/Basketball/laLakers.png").build())
                .withQuoten(quoten)
                .build();
    }
}
