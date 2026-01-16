package com.imt.mines.bank.bdd;

import static org.junit.Assert.assertEquals;

import bankAccountApp.BankAccount;
import bankAccountApp.Person;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class BankAccountBasicSteps {
    private BankAccount account;
    private double observedBalance;

    @Given("I have a new bank account")
    public void i_have_a_new_bank_account() {
        // TODO: create a new bank account with initial balance 0
        Person person = null;
        try {
            person = new Person( "Test", 'm', 22, 60, 172, "brown", "brown", "test@test.com");
        } catch (Exception e) {
            System.out.print("Unexpected failure during test setup creating accountHolder");
            e.printStackTrace();
        }
        account = new BankAccount(0, 1000, "10/01/2026", person);
    }

    @When("I check its balance")
    public void i_check_its_balance() {
        // TODO: read the balance from the account and store it in observedBalance
       observedBalance = account.getBalance();
    }

    @Then("the balance should be {int}")
    public void the_balance_should_be(Integer expected) {
        // TODO: assert that observedBalance equals expected
        // Example: assertEquals(expected.intValue(), observedBalance);
        assertEquals(expected.intValue(), (int)observedBalance);
    }
}
