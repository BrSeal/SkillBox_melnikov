package module06.task03.Clients;

import module06.task01.BankAccount.Exceptions.NotEnoughMoneyException;
import module06.task01.BankAccount.Exceptions.WithdrawalDateException;
import module06.task01.BankAccount.Exceptions.WrongMoneyAmountException;

import java.math.BigDecimal;

// Физ Лицо
// пополнение и снятие без комиссии,
public class FisClient extends Client
{
    
    public BigDecimal deposit(double amount) throws WrongMoneyAmountException
    {
        return getBankAccount().deposit(amount);
    }
    
    public BigDecimal withdraw(double amount) throws WrongMoneyAmountException, NotEnoughMoneyException, WithdrawalDateException
    {
        return getBankAccount().withdraw(amount);
    }
}
