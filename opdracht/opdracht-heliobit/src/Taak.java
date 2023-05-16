public class Taak {
    private final String naam;
    private final int tijdInMs;

    public Taak(String naam, int tijdInMs) {
        this.naam = naam;
        this.tijdInMs = tijdInMs;
    }

    public int getTijdInMs() {
        return tijdInMs;
    }

    public String getNaam() {
        return naam;
    }
}
