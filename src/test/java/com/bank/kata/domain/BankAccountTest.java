package com.bank.kata.domain;

import com.bank.kata.domain.port.StatementPrinter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

/**
 * @author lila.becha
 */
@ExtendWith(MockitoExtension.class)
public class BankAccountTest {

    private BankAccount account;

    @Mock
    private StatementPrinter statementPrinter;

    @Captor
    private ArgumentCaptor<Transaction> transactionArgumentCaptur;

    @BeforeEach
    void init() {
        account = new BankAccount(Clock.systemDefaultZone());
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

    @Test
    public void should_return_empty_balance_when_withdraw_total_balance() {
        account.deposit(1000);
        account.withdraw(1000);
        final double expectedBalance = 0.0;
        assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    public void should_return_balance_of_300_when_withdraw_amount_of_300_from_total_funds_of_600() {
        account.deposit(600);
        account.withdraw(300);
        final double expectedBalance = 300;
        assertEquals(expectedBalance, account.getBalance());
    }

    @Test
    public void should_throw_exception_when_withdraw_amount_exceeds_balance() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> account.withdraw(100));
        String expectedMessage = "Transaction cancelled";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void should_not_print_anything_when_no_transaction_found() {
        account.printStatement(statementPrinter);
        Mockito.verify(statementPrinter, never()).print(ArgumentMatchers.any(Transaction.class));
    }

    @Test
    public void should_print_one_deposit_transaction() {
        account.deposit(130);
        account.printStatement(statementPrinter);
        Mockito.verify(statementPrinter, times(1)).print(transactionArgumentCaptur.capture());

        final Transaction transaction = transactionArgumentCaptur.getValue();
        assertNotNull(transaction);
        assertEquals(TransactionType.DEPOSIT, transaction.transactionType());
        assertNotNull(transaction.transactionDate());
        assertEquals(130, transaction.amount());
        assertEquals(130, transaction.balance());
    }

    @Test
    public void should_print_two_transactions() {
        account.deposit(15000);
        account.withdraw(6000);
        account.printStatement(statementPrinter);
        Mockito.verify(statementPrinter, times(2)).print(ArgumentMatchers.any(Transaction.class));
    }
}
