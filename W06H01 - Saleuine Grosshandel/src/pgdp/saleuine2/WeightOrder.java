package pgdp.saleuine2;

public class WeightOrder extends TradeOrder {
    private final int targetWeight;

    // Konstruktor
    public WeightOrder(int targetWeight) {
        this.targetWeight = targetWeight;
    }

    // Getter
    public int getTargetWeight() {
        return targetWeight;
    }
}
