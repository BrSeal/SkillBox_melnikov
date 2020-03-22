package module06.task04.Company.Employees;

import module06.task04.Company.Company;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Manager implements Employee {
    private static final BigDecimal MANAGER_BONUS = BigDecimal.valueOf(0.05);
    private BigDecimal moneyGain;
    private BigDecimal monthSalary;
    private Company company;

    public Manager() {
        moneyGain = BigDecimal.valueOf(Math.random() * 1_000_000).setScale(2, RoundingMode.FLOOR);
        setMonthSalary();
    }

    @Override
    public void setMonthSalary() {
        monthSalary = BigDecimal.valueOf(30000).add(moneyGain.multiply(MANAGER_BONUS))
                .setScale(2, RoundingMode.FLOOR);
    }

    @Override
    public Company getCompany() {
        return company;
    }

    @Override
    public void setCompany(Company c) {

        if (c != null) {
            this.company = c;
            boolean before = company.getIncome().compareTo(TopManager.BIG_INCOME) <= 0;
            company.setIncome(company.getIncome().add(moneyGain));
            boolean after = c.getIncome().compareTo(TopManager.BIG_INCOME) > 0;
            if (before && after) company.calcSalaries();
        } else {
            boolean before = company.getIncome().compareTo(TopManager.BIG_INCOME) > 0;
            company.setIncome(company.getIncome().subtract(moneyGain));
            boolean after = company.getIncome().compareTo(TopManager.BIG_INCOME) <= 0;
            if (before && after) company.calcSalaries();
            this.company = null;
        }
    }

    public BigDecimal getMoneyGain() {
        return moneyGain;
    }

    public BigDecimal getMonthSalary() {
        return monthSalary;
    }

    @Override
    public String toString() {
        return "Manager " + getMonthSalary();
    }
}
