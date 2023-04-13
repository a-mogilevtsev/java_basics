public class IndividualBusinessman extends Client {
    public IndividualBusinessman() {
        super(0, 0.01);
    }

    public IndividualBusinessman(double amount) {
        super(amount, 0, 0.01);
    }

    @Override
    public double getAmount() {
        return super.getAmount();
    }

    @Override
    public void put(double amountToPut) {
        if(amountToPut < 1000) super.put(amountToPut);
        else super.put(amountToPut, 0.005);
    }




    @Override
    public void take(double amountToTake) {
        super.take(amountToTake);
    }
}
