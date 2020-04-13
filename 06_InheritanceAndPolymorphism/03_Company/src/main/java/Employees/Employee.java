package Employees;

import Company.Company;

import java.math.BigDecimal;

public interface Employee extends Comparable<Employee> {
    BigDecimal getMonthSalary();

    void setMonthSalary();

    Company getCompany();

    void setCompany(Company company);

    default int compareTo(Employee o2) {
        return getMonthSalary().compareTo(o2.getMonthSalary());
    }
}
