package com.bank.kata.infrastructure;

import com.bank.kata.domain.Transaction;
import com.bank.kata.domain.TransactionType;
import com.bank.kata.domain.port.StatementPrinter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Instant;

/**
 * @author lila.becha
 */
public class ConsoleStatementPrinterTest {

    private static final Instant INSTANT_DEPOSIT_TRANSACTION = Instant.parse("2021-09-13T20:55:32.366269800Z");
    private static final Instant INSTANT_WITHDRAWAL_TRANSACTION = Instant.parse("2021-09-13T21:17:27.759609500Z");

    private final ByteArrayOutputStream stream = new ByteArrayOutputStream();
    private StatementPrinter statementPrinter = new ConsoleStatementPrinter();
    private Transaction transaction;

    @BeforeEach
    void init() {
        System.setOut(new PrintStream(stream));
    }

    @Test
    public void should_print_one_deposit_transaction() {
        transaction = new Transaction(TransactionType.DEPOSIT, INSTANT_DEPOSIT_TRANSACTION, 2000.0, 4000.0);
        statementPrinter.print(transaction);

        String expected = "OperationType='DEPOSIT' | OperationDate='2021-09-13T20:55:32.366269800Z' | Amount='2000.0' | Balance='4000.0'";
        Assertions.assertEquals(expected, stream.toString().strip());
    }

    @Test
    public void should_print_one_withdrawal_transaction() {
        transaction = new Transaction(TransactionType.WITHDRAWAL, INSTANT_WITHDRAWAL_TRANSACTION, 100.0, 3000.0);
        statementPrinter.print(transaction);

        String expected = "OperationType='WITHDRAWAL' | OperationDate='2021-09-13T21:17:27.759609500Z' | Amount='100.0' | Balance='3000.0'";
        Assertions.assertEquals(expected, stream.toString().strip());
    }

}
