package com.bank.kata.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author lila.becha
 */
public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void init() {
        account = new BankAccount();
    }

    @Test
    public void should_return_empty_balance_when_no_deposit_transaction() {
        final double expectedBalance = 0.0;
        assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    public void should_return_balance_of_700_when_deposit_amount_of_700() {
        account.deposit(700);
        final double expectedBalance = 700;
        assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    public void should_return_balance_of_1300_when_deposit_amount_of_600_and_700() {
        account.deposit(600);
        account.deposit(700);
        final double expectedBalance = 1300;
        assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    public void should_return_balance_of_200_when_deposit_negative_amount_of_200() {
        account.deposit(-200);
        final double expectedBalance = 200;
        assertEquals(expectedBalance, account.getBalance());
    }
}
