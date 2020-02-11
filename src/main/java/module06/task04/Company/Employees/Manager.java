package module06.task04.Company.Employees;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Manager implements Employee
{
    private static final BigDecimal MANAGER_BONUS=BigDecimal.valueOf(0.05);
    private BigDecimal moneyGain;
    private BigDecimal monthSalary;
    
    public Manager(){
        moneyGain=BigDecimal.valueOf(Math.random()*1_000_000).setScale(2,RoundingMode.FLOOR);
        monthSalary=BigDecimal.valueOf(30000d).add(moneyGain.multiply(MANAGER_BONUS)).setScale(2, RoundingMode.FLOOR);
    }
    
    public BigDecimal getMoneyGain()
    {
        return moneyGain;
    }
    
    public BigDecimal getMonthSalary()
    {
        return monthSalary;
    }
    
    @Override
    public String toString()
    {
        return "Manager " + getMonthSalary();
    }
}
