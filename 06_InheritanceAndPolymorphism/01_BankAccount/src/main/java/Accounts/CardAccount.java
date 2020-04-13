package Accounts;

import Exceptions.*;

import java.math.BigDecimal;

public class CardAccount extends BankAccount {
    private static final double FEE = 1.01;

    @Override
    public BigDecimal withdraw(double amount) throws WrongMoneyAmountException, NotEnoughMoneyException, WithdrawalDateException {
        return super.withdraw(amount * FEE);
    }
}
