package ripico.api.domain;

import ripico.api.domain.enums.Sportart;

public final class MannschaftBuilder {
    private int mannschaftId;
    private String mannschaftsName;
    private String mannschaftLogo;
    private Sportart sportart;

    private MannschaftBuilder() {
    }

    public static MannschaftBuilder newMannschaft() {
        return new MannschaftBuilder();
    }

    public MannschaftBuilder withMannschaftId(int mannschaftId) {
        this.mannschaftId = mannschaftId;
        return this;
    }

    public MannschaftBuilder withMannschaftsName(String mannschaft) {
        this.mannschaftsName = mannschaft;
        return this;
    }

    public MannschaftBuilder withMannschaftLogo(String mannschaftLogo) {
        this.mannschaftLogo = mannschaftLogo;
        return this;
    }

    public MannschaftBuilder withSportart(Sportart sportart) {
        this.sportart = sportart;
        return this;
    }

    public Mannschaft build() {
        Mannschaft mannschaft = new Mannschaft();
        mannschaft.setMannschaftId(mannschaftId);
        mannschaft.setMannschaftsName(mannschaftsName);
        mannschaft.setMannschaftLogo(mannschaftLogo);
        mannschaft.setSportart(sportart);
        return mannschaft;
    }
}
