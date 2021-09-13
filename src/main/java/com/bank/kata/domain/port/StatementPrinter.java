package com.bank.kata.domain.port;

import com.bank.kata.domain.Transaction;

/**
 * @author lila.becha
 */
public interface StatementPrinter {

    /**
     * print transaction.
     *
     * @param transaction
     */
    void print(Transaction transaction);
}
