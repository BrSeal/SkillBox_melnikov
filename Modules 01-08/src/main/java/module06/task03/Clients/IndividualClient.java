package module06.task03.Clients;

// ИП
// пополнение с комиссией 1%, если сумма меньше 1000 рублей,
// * и 0,5%, если сумма больше либо равна 1000 рублей.

import module06.task01.BankAccount.Exceptions.NotEnoughMoneyException;
import module06.task01.BankAccount.Exceptions.WithdrawalDateException;
import module06.task01.BankAccount.Exceptions.WrongMoneyAmountException;

import java.math.BigDecimal;

public class IndividualClient extends Client {
    private static final double LESS_THAN_1000_FEE = 0.01;
    private static final double MORE_THAN_1000_FEE = 0.005;

    public BigDecimal deposit(double amount) throws WrongMoneyAmountException {
        double fee = amount < 1000 ? LESS_THAN_1000_FEE : MORE_THAN_1000_FEE;
        getBankAccount().deposit(amount * (1 - fee));
        return getBalance();
    }

    public BigDecimal withdraw(double amount) throws WrongMoneyAmountException, NotEnoughMoneyException, WithdrawalDateException {
        getBankAccount().withdraw(amount);
        return getBalance();
    }
}
