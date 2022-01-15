package pgdp.saleuine2;

import java.math.BigDecimal;

public class TradeOrder {
    private BigDecimal totalCost;
    private int currentWeight;
    private int supplyOrderCounter = 0;

    // Konstruktor, initialisiert Kosten und Gewicht mit 0
    public TradeOrder() {
        totalCost = BigDecimal.valueOf(0);
        currentWeight = 0;
    }

    // Getters
    public int getSupplyOrderCounter() {
        return supplyOrderCounter;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    // Gibt den Typ der Bestellung und dazugehörigen Infos als String zurück
    public String orderType() {
        if (this instanceof WeightOrder) {
            return "Zielgewicht: " + ((WeightOrder) this).getTargetWeight() + "g";
        } else if (this instanceof AmountOrder) {
            return "Anzahl: [" + ((AmountOrder) this).getTargetAmountAnchovies() + "," +
                    ((AmountOrder) this).getTargetAmountCrustaceans() + "," +
                    ((AmountOrder) this).getTargetAmountSardines() + "]";
        } else {
            return "Einzeln";
        }
    }

    // Gibt die Infos der Bestellung mithilfe von ordertype zurück
    public String toString() {
        return "Die Bestellung(" + orderType() + ") hat ein Gesamtgewicht von " + getCurrentWeight() +
                "g und kostet " + getTotalCost() + "PD.";
    }

    // Uberprüft ob die Bestellung schon erfüllt würde anhand des Typs der Bestellung
    public boolean isOrderFulfilled() {
        if (this instanceof WeightOrder) {
            return this.getCurrentWeight() >= ((WeightOrder) this).getTargetWeight();
        } else if (this instanceof AmountOrder) {
            return amountOrderCheck();
        } else {
            return getSupplyOrderCounter() >= 1;
        }
    }

    // Hilfsmethode für isOrderFulfilled für die Instanz der Klasse AmountOrder
    private boolean amountOrderCheck() {
        return ((AmountOrder) this).getCurrentAmountAnchovies() >= ((AmountOrder) this).getTargetAmountAnchovies() &&
                ((AmountOrder) this).getCurrentAmountCrustaceans() >=
                        ((AmountOrder) this).getTargetAmountCrustaceans() &&
                ((AmountOrder) this).getCurrentAmountSardines() >= ((AmountOrder) this).getTargetAmountSardines();
    }

    // Hilfmethode für amountOrderCheck für Anchovies
    public boolean anchoviesAmountReached(AmountOrder order) {
        return order.getCurrentAmountAnchovies() >= order.getTargetAmountAnchovies();
    }

    // Hilfmethode für amountOrderCheck für Crustaceans
    public boolean crustaceanAmountReached(AmountOrder order) {
        return order.getCurrentAmountCrustaceans() >= order.getTargetAmountCrustaceans();
    }

    // Hilfmethode für amountOrderCheck für Sardines
    public boolean sardinesAmountReached(AmountOrder order) {
        return order.getCurrentAmountSardines() >= order.getTargetAmountSardines();
    }

    // Essen in der Bestellung addieren
    public boolean supplyOrder(PinguFood supply, BigDecimal cost) {

        // Bei AmountOrder müssen jedes mal auch die Mengen an dem gegebenen Fisch hochgezöhlt werden
        // Und überprüft werden ob die Menge des gegebenen Pingufoods erreicht wurde.
        if (this instanceof AmountOrder && !isOrderFulfilled() && supply.isEdible()) {
            if (((AmountOrder) this).getAmount(supply) >= ((AmountOrder) this).getTargetAmount(supply)) {
                return false;
            }
            ((AmountOrder) this).incrementAmount(supply);
            currentWeight += supply.getWeight();
            totalCost = totalCost.add(cost);
            supplyOrderCounter++;
            return true;
        }

        // TradeOrder und WeightOrder
        if (!isOrderFulfilled() && supply.isEdible()) {
            // Gesamtgewicht und Kosten updaten
            currentWeight += supply.getWeight();
            totalCost = totalCost.add(cost);
            // Hochzählen der gegebenen Fischen
            supplyOrderCounter++;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        AmountOrder order = new AmountOrder(1, 2, 3);
        order.supplyOrder(new Anchovie(1, 100), BigDecimal.ONE);
        System.out.println(order.getCurrentAmountAnchovies());
        order.supplyOrder(new Anchovie(1, 100), BigDecimal.ONE);
        System.out.println(order.getTargetAmountAnchovies());
        System.out.println(order.getCurrentAmountAnchovies());
    }
}
