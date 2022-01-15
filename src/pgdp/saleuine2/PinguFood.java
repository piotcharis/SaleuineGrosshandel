package pgdp.saleuine2;

public class PinguFood {
    private int age;
    private int weight;

    // Konstruktor
    public PinguFood(int age, int weight) {
        this.age = age;
        this.weight = weight;
    }

    // Getters
    public int getAge() {
        return age;
    }

    public int getWeight() {
        return weight;
    }

    // Prüft, ob der Fisch essbar ist, anhand der Mindestwerten der jeweiligen Klasse
    public boolean isEdible() {
        // Anchovies
        if (this instanceof Anchovie) {
            return getAge() >= Anchovie.getMinAge() && getWeight() >= Anchovie.getMinWeight();

            // Crustaceans
        } else if (this instanceof Crustacean) {
            return true;

            // Sardines
        } else if (this instanceof Sardine) {
            return getAge() >= Sardine.getMinAge() && getWeight() >= Sardine.getMinWeight() &&
                    ((Sardine) this).getLength() >= Sardine.getMinLength();
        }
        return false;
    }

    // toString
    public String toString() {
        // Anchovies
        if (this instanceof Anchovie) {
            return "Sardelle(Alter: " + getAge() + " Jahre, Gewicht: " + getWeight() + "g)";

            // Crustaceans
        } else if (this instanceof Crustacean) {
            return "Krill(" + getWeight() + "g)";

            // Sardines
        } else if (this instanceof Sardine) {
            return "Sardine(Alter: " + getAge() + " Jahre, Gewicht: " + getWeight() + "g, Länge: " +
                    ((Sardine) this).getLength() + ")";

            // Alles andere
        } else {
            return "Alter: " + getAge() + " Jahre, Gewicht: " + getWeight() + "g";
        }
    }
}
