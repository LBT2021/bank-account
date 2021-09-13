package com.bank.kata;

import com.bank.kata.domain.BankAccount;
import com.bank.kata.domain.port.StatementPrinter;
import com.bank.kata.infrastructure.ConsoleStatementPrinter;

import java.time.Clock;

/**
 * @author lila.becha
 */
public class BankAccountApplication {

    private static final StatementPrinter statementPrinter = new ConsoleStatementPrinter();

    public static void main(String[] args) {

        // create a bank account
        BankAccount account = new BankAccount(Clock.systemDefaultZone());

        // perform deposit and withdrawal operations
        account.deposit(700);
        account.withdraw(200);
        account.deposit(3000);
        account.withdraw(1200);

        // print history of operations
        account.printStatement(statementPrinter);
    }
}
