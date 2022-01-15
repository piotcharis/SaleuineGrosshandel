package pgdp.saleuine2;

import static pgdp.PinguLib.*;

import java.math.BigDecimal;

public class PinguFoodLogistics {
    private TradeOrderQueue orderBook;
    private BigDecimal ppgAnchovies;
    private BigDecimal ppgCrustaceans;
    private BigDecimal ppgSardines;

    // Hilfsattribute
    private BigDecimal totalCostOfUnusedFood = BigDecimal.valueOf(0);
    private BigDecimal totalWeightOfUnusedFood = BigDecimal.valueOf(0);
    private int unusedFoodCounter = 0;
    private int orderCounter = 0;

    // Konstruktor
    public PinguFoodLogistics(BigDecimal ppgAnchovies, BigDecimal ppgCrustaceans, BigDecimal ppgSardines) {
        this.ppgAnchovies = ppgAnchovies;
        this.ppgCrustaceans = ppgCrustaceans;
        this.ppgSardines = ppgSardines;
        orderBook = new TradeOrderQueue();
    }

    // Getters
    public BigDecimal getPpgAnchovies() {
        return ppgAnchovies;
    }

    public BigDecimal getPpgCrustaceans() {
        return ppgCrustaceans;
    }

    public BigDecimal getPpgSardines() {
        return ppgSardines;
    }

    private BigDecimal getTotalCostOfUnusedFood() {
        return totalCostOfUnusedFood;
    }

    private BigDecimal getTotalWeightOfUnusedFood() {
        return totalWeightOfUnusedFood;
    }

    private int getUnusedFoodCounter() {
        return unusedFoodCounter;
    }

    public int getOrderCounter() {
        return orderCounter;
    }

    public TradeOrderQueue getOrderBook() {
        return orderBook;
    }

    // Neue Bestellung hinzufügen
    public void acceptNewOrder(TradeOrder order) {
        getOrderBook().add(order);
        // Hochzählen der Bestellungen insgesamt
        orderCounter++;
    }

    // Nicht genutztes Essen registrieren
    private void registerUnusedFood(PinguFood fish) {
        // Gesamtkosten für den Fisch anhand der Klasse
        if (fish instanceof Anchovie) {
            totalCostOfUnusedFood =
                    totalCostOfUnusedFood.add(getPpgAnchovies().multiply(BigDecimal.valueOf(fish.getWeight())));
        } else if (fish instanceof Crustacean) {
            totalCostOfUnusedFood =
                    totalCostOfUnusedFood.add(getPpgCrustaceans().multiply(BigDecimal.valueOf(fish.getWeight())));
        } else if (fish instanceof Sardine) {
            totalCostOfUnusedFood =
                    totalCostOfUnusedFood.add(getPpgSardines().multiply(BigDecimal.valueOf(fish.getWeight())));
        }
        // Hochzählen des nicht genutztes Essens
        unusedFoodCounter++;

        // Gewicht des Fisches zum gesamten Gewicht des nicht genutztes Essens addieren
        totalWeightOfUnusedFood = totalWeightOfUnusedFood.add(BigDecimal.valueOf(fish.getWeight()));
    }

    // Statistiken für nicht genutztes Essen in der Kosnole ausgeben
    public void printWasteStatistics() {
        System.out.println("Bisher konnten " + getUnusedFoodCounter() + " Tiere mit einem Gesamtgewicht von " +
                getTotalWeightOfUnusedFood() +
                "g nicht verwertet werden.\nClaudia und Karl-Heinz ist dadurch ein Profit von " +
                getTotalCostOfUnusedFood() + "PD entgangen.");
    }

    // Alle Bestellung in der Queue behandeln
    public void clearOrderBook() {
        System.out.println("Es können " + getOrderCounter() + " Bestellungen abgearbeitet werden.");

        while (getOrderBook().size() > 0) {
            // Bestellung rausnehmen aus der Queue
            TradeOrder order = getOrderBook().poll();

            // AmountOrder
            if (order instanceof AmountOrder) {
                // Zuerst die Anchovies
                while (!order.anchoviesAmountReached((AmountOrder) order)) {
                    // random Anchovie generieren
                    PinguFood anchovie = generateAnchovie();

                    // Überprüfen, ob die Anchovie essbar ist. Wird schon in der Methode supplyOrder überprüft, aber
                    // hier nochmal um zu entscheiden, ob die Anchovie als unused Food registriert werden soll.
                    if (anchovie.isEdible()) {
                        // Anchovie in der Bestellung addieren
                        order.supplyOrder(anchovie,
                                getPpgAnchovies().multiply(BigDecimal.valueOf(anchovie.getWeight())));
                    } else {
                        // Anchovie als unused Food registrieren
                        registerUnusedFood(anchovie);
                    }
                }

                // Dann Crustaceans, hier kann es keine nicht essbare Instanzen geben, da alle Crustaceans essbar sind.
                while (!order.crustaceanAmountReached((AmountOrder) order)) {
                    // Random crustaceans generieren
                    PinguFood crustacean = generateCrustacean();
                    // In der Bestellung addieren
                    order.supplyOrder(crustacean,
                            getPpgCrustaceans().multiply(BigDecimal.valueOf(crustacean.getWeight())));
                }

                // Als letztes Sardines addieren, gleiches Prinzip wie Anchovies
                while (!order.sardinesAmountReached((AmountOrder) order)) {
                    // Random sardine generieren
                    PinguFood sardine = generateSardine();
                    if (sardine.isEdible()) {
                        order.supplyOrder(sardine,
                                getPpgSardines().multiply(BigDecimal.valueOf(sardine.getWeight())));
                    } else {
                        registerUnusedFood(sardine);
                    }
                }

                // WeightOrder oder TradeOrder
            } else {
                while (!order.isOrderFulfilled()) {
                    // random Essen generrieren
                    PinguFood randomFood = generatePinguFood();
                    BigDecimal price;

                    // richtiges Preis für das generiertes Essen bekommen
                    if (randomFood instanceof Anchovie) {
                        price = getPpgAnchovies();
                    } else if (randomFood instanceof Crustacean) {
                        price = getPpgCrustaceans();
                    } else {
                        price = getPpgSardines();
                    }

                    // Falls es essbar ist wird es in der Bestellung addiert sonst als nicht genutzt registriert
                    if (randomFood.isEdible()) {
                        order.supplyOrder(randomFood, price.multiply(BigDecimal.valueOf(randomFood.getWeight())));
                    } else {
                        registerUnusedFood(randomFood);
                    }
                }
            }
            // toString der order aufrufen und in der Konsole ausgeben
            System.out.println(order);
            orderCounter = 0;
        }
    }

    public static void main(String[] args) {
        PinguFoodLogistics market = new PinguFoodLogistics(BigDecimal.ONE, BigDecimal.valueOf(0.5),
                BigDecimal.valueOf(2));
        AmountOrder amountOrder = new AmountOrder(2, 2, 2);
        market.acceptNewOrder(new TradeOrder());
        market.acceptNewOrder(new WeightOrder(1000));
        market.acceptNewOrder(amountOrder);
        market.clearOrderBook();
        market.printWasteStatistics();
    }

    /**
     * The following methods generate Anchovie, Crustacean or Sardine object
     * WARNING: do NOT change these methods unless you want to fail the tests
     */


    public static PinguFood generatePinguFood() {
        switch (randomInt(0, 2)) {
            case 0:
                return generateAnchovie();
            case 1:
                return generateCrustacean();
            case 2:
                return generateSardine();
            default:
                throw new SecurityException("You changed the code!");
        }
    }

    public static Anchovie generateAnchovie() {
        return new Anchovie(randomInt(0, 5), randomInt(1, 55));
    }

    public static Crustacean generateCrustacean() {
        return new Crustacean(randomInt(1, 10));
    }

    public static Sardine generateSardine() {
        return new Sardine(randomInt(0, 10), randomInt(20, 300), randomInt(1, 22));
    }

}
