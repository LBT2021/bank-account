package com.bank.kata.domain;

/**
 * @author lila.becha
 */
public class BankAccount {

    private double balance = 0.0;

    /**
     * deposit operation.
     *
     * @param amount
     */
    public void deposit(double amount) {
        throw new UnsupportedOperationException("The deposit operation is not implemented yet.");
    }

    /**
     * @return the account balance.
     */
    public double getBalance() {
        return this.balance;
    }
}
