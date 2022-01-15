package pgdp.saleuine2;

public class Anchovie extends PinguFood {

    // Mindestgrößen für Gewicht und Alter
    private static final int MIN_AGE = 1;
    private static final int MIN_WEIGHT = 5;

    // Konstruktor
    public Anchovie(int age, int weight) {
        super(age, weight);
    }

    // Getters
    public static int getMinAge() {
        return MIN_AGE;
    }

    public static int getMinWeight() {
        return MIN_WEIGHT;
    }
}
