package ripico.api.domain;

import ripico.api.domain.enums.Sportart;

public class Mannschaft {
    private int mannschaftId;
    private String mannschaftsName;
    private String mannschaftLogo;
    private Sportart sportart;

    Mannschaft() {

    }

    public int getMannschaftId() {
        return mannschaftId;
    }

    public void setMannschaftId(int mannschaftId) {
        this.mannschaftId = mannschaftId;
    }

    public String getMannschaftsName() {
        return mannschaftsName;
    }

    public void setMannschaftsName(String mannschaftsName) {
        this.mannschaftsName = mannschaftsName;
    }

    public String getMannschaftLogo() {
        return mannschaftLogo;
    }

    public void setMannschaftLogo(String mannschaftLogo) {
        this.mannschaftLogo = mannschaftLogo;
    }

    public Sportart getSportart() {
        return sportart;
    }

    public void setSportart(Sportart sportart) {
        this.sportart = sportart;
    }
}
