package Employees;

import Company.Company;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Operator implements Employee {
    private BigDecimal monthSalary;
    private Company company;

    public Operator() {
        monthSalary = (BigDecimal.valueOf(30000d + Math.random() * 10000)).setScale(2, RoundingMode.FLOOR);
    }

    @Override
    public void setMonthSalary() {
    }

    @Override
    public Company getCompany() {
        return company;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public BigDecimal getMonthSalary() {
        return monthSalary;
    }

    @Override
    public String toString() {
        return "Operator " + getMonthSalary();
    }
}