public class BankAccount {
  private double amount;

  BankAccount() {
      this.amount = 0;
  }
  BankAccount(double amount) {
      this.amount = amount;
  }
  public double getAmount() {
      return amount;
  }

  public void put(double amountToPut) {
      if(amountToPut > 0) {
          amount = amount + amountToPut;
      }
  }

  public void take(double amountToTake) {
      if(amountToTake <= amount) {
          amount = amount - amountToTake;
      } else System.out.println("Недостаточно средств для снятия");
  }

  public boolean send (BankAccount receiver, double amountToSend) {
      if(amountToSend > amount ) return false;
      else {
          amount = amount - amountToSend;
          receiver.put(amountToSend);
          return true;
      }
  }
}
