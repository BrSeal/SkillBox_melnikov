package module06.task03.Clients;

// Юрлицо
// снятие с комиссией 1%

import module06.task01.BankAccount.Exceptions.NotEnoughMoneyException;
import module06.task01.BankAccount.Exceptions.WithdrawalDateException;
import module06.task01.BankAccount.Exceptions.WrongMoneyAmountException;

import java.math.BigDecimal;

public class EntityClient extends Client {
    private static final double WITHDRAWAL_FEE = 0.01;

    public double getWithdrawalFee() {
        return WITHDRAWAL_FEE;
    }

    @Override
    public BigDecimal deposit(double amount) throws WrongMoneyAmountException {
        return getBankAccount().deposit(amount);
    }

    @Override
    public BigDecimal withdraw(double amount) throws WrongMoneyAmountException, NotEnoughMoneyException, WithdrawalDateException {
        return getBankAccount().withdraw(amount * (1 + WITHDRAWAL_FEE));
    }
}
