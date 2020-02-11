package module06.task04.Company.Employees;

import java.math.BigDecimal;

public interface Employee extends Comparable<Employee>
{
    BigDecimal getMonthSalary();
    
    default int compareTo(Employee o2)
    {
        return getMonthSalary().compareTo(o2.getMonthSalary());
    }
}
