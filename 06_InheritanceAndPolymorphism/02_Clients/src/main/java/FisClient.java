import Exceptions.*;

import java.math.BigDecimal;

// Физ Лицо
// пополнение и снятие без комиссии,
public class FisClient extends Client {

    public BigDecimal deposit(double amount) throws WrongMoneyAmountException {
        return getBankAccount().deposit(amount);
    }

    public BigDecimal withdraw(double amount) throws WrongMoneyAmountException, NotEnoughMoneyException, WithdrawalDateException {
        return getBankAccount().withdraw(amount);
    }
}
