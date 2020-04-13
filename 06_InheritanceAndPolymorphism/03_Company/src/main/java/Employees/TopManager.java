package Employees;

import Company.Company;

import java.math.BigDecimal;
import java.math.RoundingMode;

//зарплата складывается из фиксированной части и бонуса в виде 150% от заработной платы, если доход компании более 10 млн рублей
public class TopManager implements Employee {
    public static final BigDecimal BIG_INCOME = new BigDecimal(10_000_000);
    private static final double TOP_MANAGER_BONUS = 1.5;
    private static final BigDecimal SALARY = BigDecimal.valueOf(100000);
    private BigDecimal monthSalary;
    private Company company;

    public TopManager() {
        monthSalary = SALARY;
    }

    public static double getTopManagerBonus() {
        return TOP_MANAGER_BONUS;
    }

    public static BigDecimal getSALARY() {
        return SALARY;
    }

    public void setMonthSalary() {
        if (company == null) monthSalary = SALARY;

        else if (company.getIncome().compareTo(BIG_INCOME) > 0) {
            BigDecimal bonus = BigDecimal.valueOf(TOP_MANAGER_BONUS + 1);
            monthSalary = monthSalary.multiply(bonus).setScale(2, RoundingMode.FLOOR);
        } else monthSalary = SALARY;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public BigDecimal getMonthSalary() {
        return monthSalary;
    }

    @Override
    public String toString() {
        return "TopManager " + getMonthSalary();
    }
}