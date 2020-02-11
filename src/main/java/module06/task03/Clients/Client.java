package module06.task03.Clients;

import module06.task01.BankAccount.Accounts.BankAccount;
import module06.task01.BankAccount.Exceptions.NotEnoughMoneyException;
import module06.task01.BankAccount.Exceptions.WithdrawalDateException;
import module06.task01.BankAccount.Exceptions.WrongMoneyAmountException;

import java.math.BigDecimal;
import java.util.HashMap;

public abstract class Client
{
    
    private static final HashMap<Long, Client> ACCOUNTS = new HashMap<>();
    private static long accountCounter = 0;
    
    private long accountID;
    private BankAccount bankAccount;
    
    Client()
    {
        accountID = accountCounter++;
        ACCOUNTS.put(accountID, this);
        bankAccount = new BankAccount();
    }
    
    public abstract BigDecimal deposit(double amount) throws WrongMoneyAmountException;
    
    public abstract BigDecimal withdraw(double amount) throws WrongMoneyAmountException, NotEnoughMoneyException, WithdrawalDateException;
    
    public static Client getClientByNumber(long accountNumber)
    {
        return ACCOUNTS.get(accountNumber);
    }
    
    public static HashMap<Long, Client> getAccounts()
    {
        return (HashMap<Long, Client>) ACCOUNTS.clone();
    }
    
    public BigDecimal getBalance()
    {
        return bankAccount.getBalance();
    }
    
    public long getAccountID()
    {
        return accountID;
    }
    
    BankAccount getBankAccount()
    {
        return bankAccount;
    }
    
    public void setBankAccount(BankAccount bankAccount)
    {
        this.bankAccount = bankAccount;
    }
}
