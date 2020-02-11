package module06.task04.Company.Employees;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Operator implements Employee
{
    private BigDecimal monthSalary;
    
    public Operator(){
        monthSalary=(BigDecimal.valueOf(30000d+Math.random()*10000)).setScale(2, RoundingMode.FLOOR);
    }
    
    @Override
    public BigDecimal getMonthSalary()
    {
        return monthSalary;
    }
    
    @Override
    public String toString()
    {
        return "Operator " + getMonthSalary();
    }
}