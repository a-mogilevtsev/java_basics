public class PhysicalPerson extends Client {
    public PhysicalPerson() {
        super(0, 0);
    }

    public PhysicalPerson(double amount) {
        super(amount, 0, 0);
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
