public class LegalPerson extends Client {
    public LegalPerson() {
        super(0.01, 0);
    }
    public LegalPerson(double amount) {
        super(amount, 0.01, 0);
    }

    @Override
    public double getAmount() {
        return super.getAmount();
    }

    @Override
    public void put(double amountToPut) {
        super.put(amountToPut);
    }

    @Override
    public void take(double amountToTake) {
        super.take(amountToTake);
    }
}
