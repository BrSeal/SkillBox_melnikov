package module06.task01.BankAccount.Accounts;

import module06.task01.BankAccount.Exceptions.NotEnoughMoneyException;
import module06.task01.BankAccount.Exceptions.WithdrawalDateException;
import module06.task01.BankAccount.Exceptions.WrongMoneyAmountException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BankAccount {

    private BigDecimal balance;

    public BankAccount() {
        setBalance(new BigDecimal(0));
    }

    public BigDecimal deposit(double amount) throws WrongMoneyAmountException {
        if (amount > 0) {
            BigDecimal augend = new BigDecimal(amount);
            setBalance(balance.add(augend));
        } else {
            throw new WrongMoneyAmountException();
        }
        return getBalance();
    }

    public BigDecimal withdraw(double amount) throws WrongMoneyAmountException, NotEnoughMoneyException, WithdrawalDateException {
        if (amount <= 0) {
            throw new WrongMoneyAmountException();
        } else {
            BigDecimal subtrahend = new BigDecimal(amount);
            if (balance.compareTo(subtrahend) < 0) {
                throw new NotEnoughMoneyException();
            } else {
                setBalance(balance.subtract(subtrahend));
            }
        }
        return getBalance();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    private void setBalance(BigDecimal balance) {
        this.balance = balance.setScale(2, RoundingMode.FLOOR);
    }
}
