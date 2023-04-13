public class CardAccount extends BankAccount {
    // не забывайте, обращаться к методам и конструкторам родителя
    // необходимо используя super, например, super.put(10D);


    public CardAccount() {
        super();
    }

    public CardAccount(double amount) {
        super(amount);
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
        super.take(amountToTake * 1.01);
    }

    @Override
    public boolean send(BankAccount receiver, double amountToSend) {
        return super.send(receiver, amountToSend*1.01);
    }
}
