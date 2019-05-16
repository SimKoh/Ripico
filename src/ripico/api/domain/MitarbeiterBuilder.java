package ripico.api.domain;

public final class MitarbeiterBuilder {
    private String vorname;
    private String nachname;
    private String benutzername;
    private String passwort;

    private MitarbeiterBuilder() {
    }

    public static MitarbeiterBuilder newMitarbeiter() {
        return new MitarbeiterBuilder();
    }

    public MitarbeiterBuilder withVorname(String vorname) {
        this.vorname = vorname;
        return this;
    }

    public MitarbeiterBuilder withNachname(String nachname) {
        this.nachname = nachname;
        return this;
    }

    public MitarbeiterBuilder withBenutzername(String benutzername) {
        this.benutzername = benutzername;
        return this;
    }

    public MitarbeiterBuilder withPasswort(String passwort) {
        this.passwort = passwort;
        return this;
    }

    public Mitarbeiter build() {
        Mitarbeiter mitarbeiter = new Mitarbeiter();
        mitarbeiter.setVorname(vorname);
        mitarbeiter.setNachname(nachname);
        mitarbeiter.setBenutzername(benutzername);
        mitarbeiter.setPasswort(passwort);
        return mitarbeiter;
    }
}
