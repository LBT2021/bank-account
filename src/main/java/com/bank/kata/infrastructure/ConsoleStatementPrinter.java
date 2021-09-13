package com.bank.kata.infrastructure;

import com.bank.kata.domain.Transaction;
import com.bank.kata.domain.port.StatementPrinter;

/**
 * @author lila.becha
 */
public class ConsoleStatementPrinter implements StatementPrinter {

    @Override
    public void print(Transaction transaction) {
        throw new UnsupportedOperationException("The print operation is not implemented yet.");
    }
}
