package ripico.service.session;

public class SessionStorage {
    private static SessionStorage ourInstance = new SessionStorage();

    public static SessionStorage getInstance() {
        return ourInstance;
    }

    private SessionTyp sessionTyp;

    private SessionStorage() {
    }

    public SessionTyp getSessionTyp() {
        return sessionTyp;
    }

    public void setSessionTyp(SessionTyp sessionTyp) {
        this.sessionTyp = sessionTyp;
    }

    public static void invalidateSession() {
        ourInstance = new SessionStorage();
    }
}
