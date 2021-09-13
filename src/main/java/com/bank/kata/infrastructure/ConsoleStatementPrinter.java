package com.bank.kata.infrastructure;

import com.bank.kata.domain.Transaction;
import com.bank.kata.domain.port.StatementPrinter;

import java.util.StringJoiner;

/**
 * @author lila.becha
 */
public class ConsoleStatementPrinter implements StatementPrinter {

    private static final String SPERATOR = " | ";

    @Override
    public void print(Transaction transaction) {
        System.out.println(prettifyStatementTransaction(transaction));
    }

    /**
     * prettify statement transaction.
     *
     * @param transaction
     * @return
     */
    private String prettifyStatementTransaction(Transaction transaction) {
        StringJoiner stringJoiner = new StringJoiner(SPERATOR);
        return stringJoiner.add("OperationType='" + transaction.transactionType() + "'")
                .add("OperationDate='" + transaction.transactionDate() + "'")
                .add("Amount='" + transaction.amount() + "'")
                .add("Balance='" + transaction.balance() + "'").toString();
    }
}
