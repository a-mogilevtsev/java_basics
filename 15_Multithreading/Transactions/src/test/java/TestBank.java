import main.java.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.sosnina on 1/13/2022.
 */
public class TestBank {
    Bank bank;
    @Before
    public void setUp() {
        bank = new Bank();
        for(int i = 1; i <= 100; i++) {
            Account account = new Account();
            account.setAccNumber(String.valueOf(i));
            account.setMoney(70000);
            bank.addAccount(account);
        }
    }

    public static int getRandomAmount() {
        return (int)((Math.random()*(60000 - 7000)) + 7000);
    }

    public static int getRandomAccNumber() {
        return (int)((Math.random()*(100 - 1)) + 1);
    }

    @Test
    public void transferTest() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running ");
                long allBallance = bank.getSumAllAccounts();
                for(int i = 0; i < 100; i++) {
                    int amount = getRandomAmount();
                    String from = String.valueOf(getRandomAccNumber());
                    String to = String.valueOf(getRandomAccNumber());
                    bank.transfer(from, to, amount);
                }
                long allBalanceAfter = bank.getSumAllAccounts();
                Assert.assertEquals(allBalanceAfter, allBallance);
            }
        };
        for(int i = 0; i < 5; i++) {
            Thread t = new Thread(runnable);
            t.setName("ThreadNum - "  + t);
            t.start();
        }
    }
}
