package module06.task01.BankAccount.Accounts;

import module06.task01.BankAccount.Exceptions.NotEnoughMoneyException;
import module06.task01.BankAccount.Exceptions.WithdrawalDateException;
import module06.task01.BankAccount.Exceptions.WrongMoneyAmountException;

import java.math.BigDecimal;

public class CardAccount extends BankAccount {
    private static final double FEE = 1.01;

    @Override
    public BigDecimal withdraw(double amount) throws WrongMoneyAmountException, NotEnoughMoneyException, WithdrawalDateException {
        return super.withdraw(amount * FEE);
    }
}
