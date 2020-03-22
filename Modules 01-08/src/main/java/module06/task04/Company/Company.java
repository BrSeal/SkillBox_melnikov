package module06.task04.Company;

import module06.task04.Company.Employees.Employee;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Company {
    private HashSet<Employee> employees = new HashSet<>();
    private BigDecimal income = BigDecimal.ZERO;

    public HashSet<Employee> getEmployees() {
        return new HashSet<>(employees);
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income.setScale(2, RoundingMode.FLOOR);
    }

    public boolean hire(Employee e) {
        e.setCompany(this);
        return employees.add(e);
    }

    public boolean hireAll(ArrayList<Employee> staff) {
        for (Employee person : staff) if (!hire(person)) return false;
        return true;
    }

    public boolean fire(Employee e) {
        if (e.getCompany() == null) return false;
        e.setCompany(null);
        return employees.remove(e);
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        return getEmployeesSortBySalary(false, count);
    }

    public List<Employee> getTopSalaryStaff(int count) {
        return getEmployeesSortBySalary(true, count);
    }

    private List<Employee> getEmployeesSortBySalary(boolean reverseOrder, int count) {
        ArrayList<Employee> e = new ArrayList<>(employees);
        if (reverseOrder) e.sort(Comparator.reverseOrder());
        else Collections.sort(e);
        if (count >= e.size()) return e;
        return e.subList(0, count);
    }

    public void calcSalaries() {
        employees.forEach(Employee::setMonthSalary);
    }

}
