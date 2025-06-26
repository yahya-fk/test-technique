package acount;

import transaction.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account implements AccountService {
    private int balance = 0;
    private final List<Transaction> transactions = new ArrayList<>();

    public Account() {
        System.out.println("Account created");
    }

    @Override
    public void deposit(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive");
        balance += amount;
        transactions.add(new Transaction(LocalDate.now().toString(), amount, balance));
    }

    @Override
    public void withdraw(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive");
        balance -= amount;
        transactions.add(new Transaction(LocalDate.now().toString(), -amount, balance));
    }

    @Override
    public void printStatement() {
        System.out.println("DATE       || AMOUNT || BALANCE");
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            System.out.printf("%-10s ||%7d || %8d%n", t.getDate(), t.getAmount(), t.getBalance());
        }
    }
}
