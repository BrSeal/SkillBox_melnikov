package module06.task04.Company;

import module06.task04.Company.Employees.Employee;
import module06.task04.Company.Employees.Manager;
import module06.task04.Company.Employees.TopManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Company
{
	private HashSet<Employee> employees = new HashSet<>();
	private BigDecimal income = BigDecimal.ZERO;
	
	public HashSet<Employee> getEmployees()
	{
		return new HashSet<>(employees);
	}
	
	public BigDecimal getIncome()
	{
		return income;
	}
	
	private void setIncome(BigDecimal income)
	{
		this.income = income.setScale(2, RoundingMode.FLOOR);
	}
	
	public boolean hire(Employee e)
	{
		
		if (e instanceof Manager)
		{
			boolean beforeHiringComparing = income.compareTo(TopManager.getBigIncome()) <= 0;
			setIncome(income.add(((Manager) e).getMoneyGain()));
			boolean afterHiringComparing = income.compareTo(TopManager.getBigIncome()) > 0;
            if (beforeHiringComparing && afterHiringComparing)
            {
                calcTopManagerSalaries();
            }
		}
		else if (e instanceof TopManager)
		{
			((TopManager) e).setCompany(this);
			((TopManager) e).setMonthSalary();
		}
		return employees.add(e);
	}
	
	public boolean hireAll(ArrayList<Employee> peopleToHire)
	{
		for (Employee person : peopleToHire)
            if (!hire(person))
            {
                return false;
            }
		return true;
	}
	
	public boolean fire(Employee e)
	{
		if (e instanceof Manager)
		{
			boolean beforeHiringComparing = income.compareTo(TopManager.getBigIncome()) > 0;
			
			setIncome(income.subtract(((Manager) e).getMoneyGain()));
			
			boolean afterHiringComparing = income.compareTo(TopManager.getBigIncome()) <= 0;
            
            if (beforeHiringComparing && afterHiringComparing)
           {
               
                calcTopManagerSalaries();
           }
		}
		else if (e instanceof TopManager)
		{
			((TopManager) e).setCompany(null);
			((TopManager) e).setMonthSalary();
		}
		return employees.remove(e);
	}
	
	public List<Employee> getLowestSalaryStaff(int count)
	{
		ArrayList<Employee> list = getEmployeesSortBySalary();
        
        if (count >= list.size())
        {
            return list;
        }
		return list.subList(0, count);
	}
	
	public List<Employee> getTopSalaryStaff(int count)
	{
		ArrayList<Employee> list = getEmployeesSortBySalary();
		
		Collections.reverse(list);
        if (count >= list.size())
        {
            return list;
        }
		return list.subList(0, count);
	}
	
	private ArrayList<Employee> getEmployeesSortBySalary()
	{
		ArrayList<Employee> employees = new ArrayList<>(this.employees);
		Collections.sort(employees);
		return employees;
	}
	
	private void calcTopManagerSalaries()
	{
		employees.forEach(e ->
		{
			if (e instanceof TopManager)
			{
				((TopManager) e).setMonthSalary();
			}
		});
	}
	
}
