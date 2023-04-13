package main.java;

public class Account implements Comparable<Account> {

    private volatile long money;
    private String accNumber;

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    @Override
    public int compareTo(Account o) {
        return this.getAccNumber().compareTo(o.getAccNumber());
    }
}
