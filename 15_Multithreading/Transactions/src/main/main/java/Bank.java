package main.java;


import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Bank {

    private  Map<String, Account> accounts;
    private  Map<String, Account> bannedAccounts;

    public Bank() {
        accounts = new HashMap<>();
        bannedAccounts = new HashMap<>();
    }

    private final Random random = new Random();

    public boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        if (bannedAccounts.size() > accounts.size() * 0.05) return false;
        else return random.nextBoolean();
    }

    public void transfer(String fromAccountNum, String toAccountNum, long amount) {

        try {
            if (amount > 50000) {
                if(isFraud(fromAccountNum,toAccountNum, amount)) {
                    banAccount(fromAccountNum);
                    banAccount(toAccountNum);
                }
            }
        } catch (InterruptedException iex) {
            iex.printStackTrace();
        }
        try {
            Account from = getAccIfExist(fromAccountNum);
            Account to = getAccIfExist(toAccountNum);
            if (from.getMoney() >= amount) {
                if(from.compareTo(to) > 0) {
                    synchronized (from) {
                        synchronized (to) {
                            from.setMoney(from.getMoney() - amount);
                            to.setMoney(to.getMoney() + amount);
                        }
                    }
                } else {
                    synchronized (to) {
                        synchronized (from) {
                            from.setMoney(from.getMoney() - amount);
                            to.setMoney(to.getMoney() + amount);
                        }
                    }
                }
            } else {
                System.out.println("There is not enough money");
            }
        } catch (IllegalArgumentException iee) {
            System.out.println(iee.getMessage());
        }



    }

    public void banAccount(String accountNum) {
        bannedAccounts.put(accountNum, accounts.get(accountNum));
        accounts.remove(accountNum);
    }

    public Account getAccIfExist (String accountNum) throws IllegalArgumentException {
        Account account = accounts.get(accountNum);
        if(account == null) {
            if(bannedAccounts.containsKey(accountNum)) {
                throw new IllegalArgumentException(String.format("Account with number - %s was banned \n", accountNum));
            } else {
                throw new IllegalArgumentException(String.format("\"There is no account with this account number  %s \n", accountNum));
            }
        } else return account;
    }


    public void addAccount(Account account) {
        accounts.put(account.getAccNumber(), account);
    }


    public long getBalance(String accountNum) {
        if(accounts.containsKey(accountNum))
            synchronized (accounts.get(accountNum)) {
                return accounts.get(accountNum).getMoney();
            }
        else return -1;
    }

    public long getSumAllAccounts() {
        long allBalance = 0;
        for (String accNum : accounts.keySet()) {
            allBalance += getBalance(accNum);
        }
        for (String accNum : bannedAccounts.keySet()) {
               allBalance += bannedAccounts.get(accNum).getMoney();
        }
        return allBalance;
    }
}