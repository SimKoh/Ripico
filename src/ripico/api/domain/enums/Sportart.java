package ripico.api.domain.enums;

public enum Sportart {
    FUSSBALL(1),
    BASKETBALL(2),
    EISHOCKEY(3);

    private int id;

    Sportart(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
