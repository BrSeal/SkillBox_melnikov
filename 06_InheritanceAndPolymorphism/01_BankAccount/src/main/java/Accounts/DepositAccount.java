package Accounts;

import Exceptions.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DepositAccount extends BankAccount {
    final static ChronoUnit UNITS = ChronoUnit.MONTHS;
    final static long WITHDRAWAL_TIME = 1;

    private LocalDate nearestWithdrawalTime;

    @Override
    public BigDecimal deposit(double amount) throws WrongMoneyAmountException {
        nearestWithdrawalTime = LocalDate.now().plus(WITHDRAWAL_TIME, UNITS);
        return super.deposit(amount);
    }

    @Override
    public BigDecimal withdraw(double amount) throws WrongMoneyAmountException, NotEnoughMoneyException, WithdrawalDateException {
        if (LocalDate.now().isAfter(nearestWithdrawalTime)) {
            return super.withdraw(amount);
        }
        throw new WithdrawalDateException();
    }

    public LocalDate getLastDepositDate() {
        return nearestWithdrawalTime.minus(WITHDRAWAL_TIME, UNITS);
    }
}
