package pgdp.saleuine2;

public class Sardine extends PinguFood {
    // Finale Attribute für Mindestgrößen
    private static final int MIN_AGE = 1;
    private static final int MIN_WEIGHT = 100;
    private static final int MIN_LENGTH = 14;
    // Zusätzliches Merkmal der Sardines
    private int length;

    // Konstruktor
    public Sardine(int age, int weight, int length) {
        super(age, weight);
        this.length = length;
    }

    // Getters
    public static int getMinAge() {
        return MIN_AGE;
    }

    public static int getMinWeight() {
        return MIN_WEIGHT;
    }

    public static int getMinLength() {
        return MIN_LENGTH;
    }

    public int getLength() {
        return length;
    }
}
