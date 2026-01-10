package bankAccountApp;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;

import bankAccountApp.BankAccount;
import org.junit.Test;

public class BankAccountTest {
    int accountId = 0;
    int initMoney = 1000;
    int withdrawLimit = 500;
    BankAccount bankAccount = null;
    Bank bank = null;
    Person person = null;


    @Before
    public void setup() {
        char gender = 'm';
        int age = 22, weight = 190;
        float height = 172;
        String name = "Test", hairColor = "brown", eyeColor = "brown", email = "test@test.com";
        try {
            person = new Person(name, gender, age, weight, height, hairColor, eyeColor, email);
        } catch (Exception e) {
            System.out.print("Unexpected failure during test setup creating accountHolder");
            e.printStackTrace();
        }
        bank = new Bank();
        bankAccount = new BankAccount(initMoney, withdrawLimit, "10/01/2026", person);
    }

    @Test
    public void test_deposit() {
        int deposit = 100;
        bank.addAccount(bankAccount, accountId);

        assertEquals(1000, bankAccount.getBalance(), 0f);
        boolean res = bankAccount.depositMoney(deposit);
        assertTrue(res);
        assertEquals(1100, bankAccount.getBalance(), 0f);
    }

    @Test
    public void test_deposit_fail() {
        int deposit = -100;
        bank.addAccount(bankAccount, accountId);

        assertEquals(1000, bankAccount.getBalance(), 0f);
        boolean res = bankAccount.depositMoney(deposit);
        assertFalse(res);
        assertEquals(1000, bankAccount.getBalance(), 0f);
    }

    @Test
    public void test_withdrawMoney() throws Exception {
        int withdraw = 100;
        bank.addAccount(bankAccount, accountId);

        assertEquals(1000, bankAccount.getBalance(), 0f);
        boolean res = bankAccount.withdrawMoney(withdraw);
        assertTrue(res);
        assertEquals(100, bankAccount.getAmountWithdrawn(), 0f);
        assertEquals(900, bankAccount.getBalance(), 0f);

    }

    @Test
    public void test_withdrawMoney_fail() throws Exception {
        int withdraw = 6000;
        bank.addAccount(bankAccount, accountId);

        assertEquals(1000, bankAccount.getBalance(), 0f);
        boolean res = bankAccount.withdrawMoney(withdraw);
        assertFalse(res);
        assertEquals(0, bankAccount.getAmountWithdrawn(), 0f);
        assertEquals(1000, bankAccount.getBalance(), 0f);

    }

}