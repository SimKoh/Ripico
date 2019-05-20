package ripico.api.domain.enums;

public enum Sportart {
    FUSSBALL(1, "Fussball"),
    BASKETBALL(2, "Basketball"),
    EISHOCKEY(3, "Eishockey");

    private int id;
    private String bezeichnung;

    Sportart(int id, String bezeichnung) {
        this.id = id;
        this.bezeichnung = bezeichnung;
    }

    public int getId() {
        return id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public static Sportart getSportartFromId(int id) {
        for (Sportart value : values()) {
            if (value.id == id) {
                return value;
            }
        }
        throw new IllegalArgumentException();
    }
}
