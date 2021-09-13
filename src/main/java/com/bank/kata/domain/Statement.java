package com.bank.kata.domain;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lila.becha
 */
public class Statement {

    private List<Transaction> transactions = new ArrayList<>();

    /**
     * add transaction to the statement.
     *
     * @param transactionType
     * @param transactionDate
     * @param amount
     * @param balance
     */
    public void addTransaction(TransactionType transactionType, Instant transactionDate, double amount, double balance) {
        transactions.add(new Transaction(transactionType, transactionDate, amount, balance));
    }

    /**
     * @return transactions list.
     */
    public List<Transaction> getTransactions() {
        return this.transactions;
    }
}
