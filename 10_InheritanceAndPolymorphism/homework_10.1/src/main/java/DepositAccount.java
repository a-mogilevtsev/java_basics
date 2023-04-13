import java.time.LocalDate;

public class DepositAccount extends BankAccount {
    private LocalDate lastIncome;
    public DepositAccount() {
        super();
    }

    public DepositAccount(double amount) {
        super(amount);
    }

    @Override
    public double getAmount() {
        return super.getAmount();
    }

    @Override
    public void put(double amountToPut) {
        lastIncome = LocalDate.now();
        super.put(amountToPut);
    }

    @Override
    public void take(double amountToTake) {
        if(LocalDate.now().isAfter(lastIncome.plusMonths(1)))
        super.take(amountToTake);
    }

    @Override
    public boolean send(BankAccount receiver, double amountToSend) {
        if(LocalDate.now().isAfter(lastIncome.plusMonths(1)))
        return super.send(receiver, amountToSend);
        else return false;
    }
}
