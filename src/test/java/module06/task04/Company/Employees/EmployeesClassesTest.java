package module06.task04.Company.Employees;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

class EmployeesClassesTest
{
	@Test
	void operatorSalaryTest()
	{
		Employee operator = new Operator();
		
		assertTrue(operator.getMonthSalary().compareTo(BigDecimal.valueOf(30000)) > 0);
		assertTrue(operator.getMonthSalary().compareTo(BigDecimal.valueOf(40000)) <= 0);
	}
    
    @Test
    void managerSalaryTest()
    {
        Employee manager = new Manager();
        
        assertTrue(manager.getMonthSalary().compareTo(BigDecimal.valueOf(30000))>0);
        assertTrue(manager.getMonthSalary().compareTo(BigDecimal.valueOf(80000))<=0);
    }
    
    @Test
    void topManagerSalaryTest()
    {
        Employee topManager = new TopManager();
        
        assertTrue(topManager.getMonthSalary().compareTo(BigDecimal.valueOf(30000))>0);
        assertTrue(topManager.getMonthSalary().compareTo(BigDecimal.valueOf(40000))<=0);
    }
}