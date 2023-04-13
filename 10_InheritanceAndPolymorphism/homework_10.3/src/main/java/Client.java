public abstract class Client {
    private double amount;
    private double cashOutTax;
    private double cashInTax;

    public Client(double cashOutTax, double cashInTax) {
        amount = 0;
        this.cashInTax = cashOutTax;
        this.cashOutTax = cashInTax;
    }
    public Client(double amount, double cashOutTax, double cashInTax) {
        this.amount = amount;
        this.cashInTax = cashInTax;
        this.cashOutTax = cashOutTax;
    }

    public double getCashInTax() {
        return cashInTax;
    }

    public double getAmount() {
        return amount;
    }

    public void put(double amountToPut) {
        put(amountToPut, cashInTax);
    }

    public void put(double amountToPut, double cashInTaxProgressive) {
        double commission = amountToPut * cashInTaxProgressive;
        amount = amount + amountToPut - commission;
    }



    public void take(double amountToTake) {
        double amountToTakeWithTax = amountToTake * (1 + cashOutTax);
        if(amountToTakeWithTax <= amount) {
            amount = amount - amountToTakeWithTax;
        }
    }

}
