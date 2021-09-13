package com.bank.kata.domain;

import com.bank.kata.domain.port.StatementPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

/**
 * @author lila.becha
 */
@ExtendWith(MockitoExtension.class)
public class StatementTest {

    private static final Instant INSTANT_DEPOSIT_TRANSACTION = Instant.parse("2021-09-13T20:55:32.366269800Z");
    private static final Instant INSTANT_WITHDRAWAL_TRANSACTION = Instant.parse("2021-09-13T21:17:27.759609500Z");

    private Statement statement;

    @Mock
    private StatementPrinter statementPrinter;

    @Captor
    private ArgumentCaptor<Transaction> transactionArgumentCaptur;

    @BeforeEach
    void init() {
        statement = new Statement();
    }

    @Test
    public void should_return_empty_transactions_list_when_no_transaction_statement_history() {
        assertNotNull(statement.getTransactions());
        assertEquals(0, statement.getTransactions().size());
    }

    @Test
    public void should_add_one_deposit_transaction_on_statement_history() {

        statement.addTransaction(TransactionType.DEPOSIT, INSTANT_DEPOSIT_TRANSACTION, 200.0, 300.0);

        assertNotNull(statement.getTransactions());
        assertEquals(1, statement.getTransactions().size());

        final Transaction transaction = statement.getTransactions().get(0);

        assertAll(
                () -> assertEquals(TransactionType.DEPOSIT, transaction.transactionType()),
                () -> assertEquals(INSTANT_DEPOSIT_TRANSACTION, transaction.transactionDate()),
                () -> assertEquals(200.0, transaction.amount()),
                () -> assertEquals(300.0, transaction.balance())
        );
    }

    @Test
    public void should_add_one_withdraw_transaction_on_statement_history() {

        statement.addTransaction(TransactionType.WITHDRAWAL, INSTANT_WITHDRAWAL_TRANSACTION, 100.0, 500.0);

        assertNotNull(statement.getTransactions());
        assertEquals(1, statement.getTransactions().size());

        final Transaction transaction = statement.getTransactions().get(0);

        assertAll(
                () -> assertEquals(TransactionType.WITHDRAWAL, transaction.transactionType()),
                () -> assertEquals(INSTANT_WITHDRAWAL_TRANSACTION, transaction.transactionDate()),
                () -> assertEquals(100.0, transaction.amount()),
                () -> assertEquals(500.0, transaction.balance())
        );
    }

    @Test
    public void should_add_two_transactions_on_statement_history() {
        final List<Transaction> expectedTransactions = Arrays.asList(
                new Transaction(TransactionType.DEPOSIT, INSTANT_DEPOSIT_TRANSACTION, 700.0, 1500.0),
                new Transaction(TransactionType.WITHDRAWAL, INSTANT_WITHDRAWAL_TRANSACTION, 100.0, 800.0)
        );

        statement.addTransaction(TransactionType.DEPOSIT, INSTANT_DEPOSIT_TRANSACTION, 700.0, 1500.0);
        statement.addTransaction(TransactionType.WITHDRAWAL, INSTANT_WITHDRAWAL_TRANSACTION, 100.0, 800.0);

        assertNotNull(statement.getTransactions());
        assertEquals(expectedTransactions.size(), statement.getTransactions().size());
    }

    @Test
    public void should_not_print_anything_when_no_transaction_found() {
        statement.print(statementPrinter);
        Mockito.verify(statementPrinter, never()).print(ArgumentMatchers.any(Transaction.class));
    }

    @Test
    public void should_print_one_deposit_transaction() {

        statement.addTransaction(TransactionType.DEPOSIT, INSTANT_DEPOSIT_TRANSACTION, 200.0, 500.0);
        statement.print(statementPrinter);

        Mockito.verify(statementPrinter, times(1)).print(transactionArgumentCaptur.capture());

        final Transaction transaction = transactionArgumentCaptur.getValue();
        assertNotNull(transaction);

        assertAll(
                () -> assertEquals(TransactionType.DEPOSIT, transaction.transactionType()),
                () -> assertEquals(INSTANT_DEPOSIT_TRANSACTION, transaction.transactionDate()),
                () -> assertEquals(200.0, transaction.amount()),
                () -> assertEquals(500.0, transaction.balance())
        );
    }

    @Test
    public void should_print_one_withdrawal_transaction() {

        statement.addTransaction(TransactionType.WITHDRAWAL, INSTANT_WITHDRAWAL_TRANSACTION, 1300.0, 800.0);
        statement.print(statementPrinter);

        Mockito.verify(statementPrinter, times(1)).print(transactionArgumentCaptur.capture());

        final Transaction transaction = transactionArgumentCaptur.getValue();
        assertNotNull(transaction);

        assertAll(
                () -> assertEquals(TransactionType.WITHDRAWAL, transaction.transactionType()),
                () -> assertEquals(INSTANT_WITHDRAWAL_TRANSACTION, transaction.transactionDate()),
                () -> assertEquals(1300.0, transaction.amount()),
                () -> assertEquals(800.0, transaction.balance())
        );
    }

}
