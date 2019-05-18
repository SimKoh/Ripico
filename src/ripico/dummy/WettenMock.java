package ripico.dummy;

import ripico.api.domain.SpielBuilder;
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
        Map<QuotenArt, Float> quoten = new EnumMap<>(QuotenArt.class);
        quoten.put(QuotenArt.HEIM, 3.02f);
        quoten.put(QuotenArt.UNENTSCHIEDEN, 2.57f);
        quoten.put(QuotenArt.AUSWAERTS, 1.87f);
        return SpielBuilder
                .newSpiel()
                .withDatum(new GregorianCalendar(2019, Calendar.MAY, 27).getTime())
                .withSportart("Fußball")
                .withMannschaftHeim("BVB")
                .withMannschaftHeimLogoPfad("/resources/imgs/logos/Fußball/bvb.png")
                .withMannschaftAuswaerts("Barcelona")
                .withMannschaftAuswaertsLogoPfad("/resources/imgs/logos/Fußball/barcelona.png")
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
                .withSportart("Eishockey")
                .withMannschaftHeim("Kölner Haie")
                .withMannschaftHeimLogoPfad("/resources/imgs/logos/Eishockey/koelnerHaie.png")
                .withMannschaftAuswaerts("Düsseldorfer EG")
                .withMannschaftAuswaertsLogoPfad("/resources/imgs/logos/Eishockey/duesseldorferEG.png")
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
                .withSportart("Basketball")
                .withMannschaftHeim("Boston Celtics")
                .withMannschaftHeimLogoPfad("/resources/imgs/logos/Basketball/bostonCeltics.png")
                .withMannschaftAuswaerts("Miami Heat")
                .withMannschaftAuswaertsLogoPfad("/resources/imgs/logos/Basketball/miamiHeat.png")
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
                .withSportart("Basketball")
                .withMannschaftHeim("Chicago Bulls")
                .withMannschaftHeimLogoPfad("/resources/imgs/logos/Basketball/chicagoBulls.png")
                .withMannschaftAuswaerts("LA Lakers")
                .withMannschaftAuswaertsLogoPfad("/resources/imgs/logos/Basketball/laLakers.png")
                .withQuoten(quoten)
                .build();
    }
}
