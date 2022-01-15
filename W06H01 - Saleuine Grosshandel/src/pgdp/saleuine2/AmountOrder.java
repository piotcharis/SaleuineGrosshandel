package pgdp.saleuine2;

public class AmountOrder extends TradeOrder {
    // Final Attributen
    private final int targetAmountAnchovies;
    private final int targetAmountCrustaceans;
    private final int targetAmountSardines;


    private int currentAmountAnchovies;
    private int currentAmountCrustaceans;
    private int currentAmountSardines;

    // Konstruktor
    public AmountOrder(int targetAmountAnchovies, int targetAmountCrustaceans, int targetAmountSardines) {
        this.targetAmountAnchovies = targetAmountAnchovies;
        this.targetAmountCrustaceans = targetAmountCrustaceans;
        this.targetAmountSardines = targetAmountSardines;

        currentAmountAnchovies = 0;
        currentAmountCrustaceans = 0;
        currentAmountSardines = 0;
    }

    // Getters
    public int getTargetAmountAnchovies() {
        return targetAmountAnchovies;
    }

    public int getTargetAmountCrustaceans() {
        return targetAmountCrustaceans;
    }

    public int getTargetAmountSardines() {
        return targetAmountSardines;
    }

    public int getCurrentAmountAnchovies() {
        return currentAmountAnchovies;
    }

    public int getCurrentAmountCrustaceans() {
        return currentAmountCrustaceans;
    }

    public int getCurrentAmountSardines() {
        return currentAmountSardines;
    }

    // Aufzählen der Mengen anhand der Klasse des Fisches
    public void incrementAmount(PinguFood fish) {
        if (fish instanceof Anchovie) {
            currentAmountAnchovies++;
        }
        if (fish instanceof Crustacean) {
            currentAmountCrustaceans++;
        }
        if (fish instanceof Sardine) {
            currentAmountSardines++;
        }
    }

    public int getAmount(PinguFood fish) {
        if (fish instanceof Anchovie) {
            return getCurrentAmountAnchovies();
        }
        if (fish instanceof Crustacean) {
            return getCurrentAmountCrustaceans();
        } else {
            return getCurrentAmountSardines();
        }
    }

    public int getTargetAmount(PinguFood fish) {
        if (fish instanceof Anchovie) {
            return getTargetAmountAnchovies();
        }
        if (fish instanceof Crustacean) {
            return getTargetAmountCrustaceans();
        } else {
            return getTargetAmountSardines();
        }
    }

}
