package module06.task04.Company;

import module06.task04.Company.Employees.Employee;
import module06.task04.Company.Employees.Manager;
import module06.task04.Company.Employees.Operator;
import module06.task04.Company.Employees.TopManager;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    @Test
    void getEmployees1() {
        Company company = new Company();
        ArrayList<Employee> e = new ArrayList<>();
        Employee worker;
        for (int i = 0; i < 300; i++) {
            worker = new Operator();
            e.add(worker);
            worker = new Manager();
            e.add(worker);
            worker = new TopManager();
            e.add(worker);
        }
        HashSet<Employee> expected = new HashSet<>(e);
        company.hireAll(e);
        assertEquals(expected, company.getEmployees());
    }

    @Test
    void getEmployees2() {
        Company company = new Company();
        ArrayList<Employee> e = new ArrayList<>();
        Employee worker;
        for (int i = 0; i < 300; i++) {
            worker = new Operator();
            e.add(worker);
            worker = new Manager();
            e.add(worker);
            worker = new TopManager();
            e.add(worker);
        }
        company.hireAll(e);
        HashSet<Employee> cloned = company.getEmployees();
        cloned.add(new Operator());
        assertNotEquals(cloned, company.getEmployees());
    }

    @Test
    void setIncome1() {
        Company c = new Company();
        assertEquals(0, c.getIncome().compareTo(BigDecimal.ZERO));
    }

    @Test
    void hire1() {
        Company c = new Company();
        Manager m = new Manager();
        c.hire(m);
        assertEquals(0, c.getIncome().compareTo(m.getMoneyGain()));
    }

    @Test
    void hire2() {
        Company c = new Company();
        BigDecimal expected = BigDecimal.ZERO;
        Manager m;
        for (int i = 0; i < 100; i++) {
            m = new Manager();
            c.hire(m);
            expected = expected.add(m.getMoneyGain());
        }
        assertEquals(0, c.getIncome().compareTo(expected));
    }

    @Test
    void hire3() {
        Company c = new Company();
        Employee m;

        for (int i = 0; i < 500; i++) {
            if (i % 8 == 0) {
                m = new Manager();
            } else if (i % 37 == 0) {
                m = new TopManager();
            } else {
                m = new Operator();
            }
            c.hire(m);
        }

        BigDecimal expected = BigDecimal.ZERO;
        for (Employee e : c.getEmployees()) {
            if (e instanceof Manager) {
                expected = expected.add(((Manager) e).getMoneyGain());
            }
        }
        assertEquals(0, c.getIncome().compareTo(expected));
    }

    @Test
    void hire4() {
        Employee e = new Operator();
        Company c = new Company();
        c.hire(e);
        assertTrue(c.getEmployees().contains(e));
    }

    @Test
    void hire5() {
        Employee e = new Operator();
        Company c = new Company();
        c.hire(e);
        assertFalse(c.hire(e));
    }

    @Test
    void hireAll1() {
        Company company = new Company();
        ArrayList<Employee> e = new ArrayList<>();
        Employee worker;

        for (int i = 0; i < 300; i++) {
            worker = new Operator();
            e.add(worker);
            worker = new Manager();
            e.add(worker);
            worker = new TopManager();
            e.add(worker);
        }
        assertTrue(company.hireAll(e));
        assertFalse(company.hireAll(e));
    }

    @Test
    void hireAll2() {
        Company company = new Company();
        ArrayList<Employee> e = new ArrayList<>();
        Employee worker;

        for (int i = 0; i < 300; i++) {
            worker = new Operator();
            e.add(worker);
            worker = new Manager();
            e.add(worker);
            worker = new TopManager();
            e.add(worker);
        }
        company.hireAll(e);
        assertTrue(company.getEmployees().containsAll(e));
    }

    @Test
    void fire1() {
        Company company = new Company();
        Operator o = new Operator();
        company.hire(o);
        company.fire(o);
        assertFalse(company.getEmployees().contains(o));
    }

    @Test
    void fire2() {
        Company c = new Company();
        Employee m;
        Employee manToFire = new Manager();

        for (int i = 0; i < 500; i++) {
            if (i % 8 == 0) {
                m = new Manager();
            } else if (i % 37 == 0) {
                m = new TopManager();
            } else {
                m = new Operator();
            }
            c.hire(m);
        }
        assertFalse(c.fire(manToFire));
    }

    @Test
    void fire3() {
        Company c = new Company();
        Employee m;
        Employee manToFire = null;
        for (int i = 0; i < 500; i++) {
            if (i % 8 == 0) {
                m = new Manager();
            } else if (i % 37 == 0) {
                m = new TopManager();
            } else {
                m = new Operator();
            }
            c.hire(m);
        }
        while (manToFire == null) {
            for (Employee e : c.getEmployees()) manToFire = Math.random() > 0.05 ? e : null;
        }
        assertTrue(c.fire(manToFire));
    }

    @Test
    void fire4() {
        Company c = new Company();
        Employee m;
        Employee manToFire = null;
        for (int i = 0; i < 500; i++) {
            if (i % 8 == 0) {
                m = new Manager();
            } else if (i % 37 == 0) {
                m = new TopManager();
            } else {
                m = new Operator();
            }
            c.hire(m);
        }
        while (manToFire == null) {
            for (Employee e : c.getEmployees()) manToFire = Math.random() > 0.05 ? e : null;
        }
        c.fire(manToFire);
        assertFalse(c.getEmployees().contains(manToFire));
    }


    @Test
    void fire6() {
        Company c = new Company();
        TopManager e = new TopManager();
        c.hire(e);
        c.fire(e);
        assertNull(e.getCompany());
    }


    @Test
    void fire7() {
        Company c = new Company();
        Manager e = new Manager();
        System.out.println(e.getMoneyGain());
        c.hire(e);
        c.fire(e);
        System.out.println(c.getIncome());
        assertEquals(0, BigDecimal.ZERO.compareTo(c.getIncome()));
    }


    @Test
    void fire8() {
        Company c = new Company();
        Manager m = new Manager();
        TopManager top = new TopManager();
        c.hire(m);
        c.hire(top);
        c.fire(m);
        assertEquals(TopManager.getSALARY(), top.getMonthSalary());
    }

    @Test
    void getTopSalaryStaff1() {
        Company company = new Company();
        for (int i = 50000; i > 0; i -= 1000) {
            Manager e = new Manager();
            company.hire(e);
        }
        assertEquals(5, company.getTopSalaryStaff(5).size());
    }

    @Test
    void getTopSalaryStaff2() {
        Company company = new Company();
        ArrayList<Employee> employees = new ArrayList<>();
        Manager e;
        for (int i = 50_000; i > 0; i -= 1000) {
            e = new Manager();
            company.hire(e);
            employees.add(e);
        }
        Collections.sort(employees);
        Collections.reverse(employees);
        assertEquals(employees, company.getTopSalaryStaff(10000));
    }

    @Test()
    void getTopSalaryStaff3() {
        Company company = new Company();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> company.getTopSalaryStaff(-1));
        assertEquals(IllegalArgumentException.class, thrown.getClass());
    }

    @Test
    void getTopSalaryStaff4() {
        Company company = new Company();
        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 50000; i > 0; i -= 1000) {
            Manager e = new Manager();
            company.hire(e);
            employees.add(e);
        }
        Collections.sort(employees);
        Collections.reverse(employees);
        assertEquals(employees.subList(0, 10), company.getTopSalaryStaff(10));
    }

    @Test
    void getTopSalaryStaff5() {
        Company company = new Company();
        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 50000; i > 0; i -= 1000) {
            Manager e = new Manager();
            company.hire(e);
            employees.add(e);
        }
        company.getEmployees().forEach(e ->
        {
            if (Math.random() > 0.5) {
                company.fire(e);
                employees.remove(e);
            }
        });
        Collections.sort(employees);
        Collections.reverse(employees);
        assertEquals(employees.subList(0, 10), company.getTopSalaryStaff(10));
    }

    @Test
    void getLowestSalaryStaff1() {
        Company company = new Company();
        for (int i = 50000; i > 0; i -= 1000) {
            Manager e = new Manager();
            company.hire(e);
        }
        assertEquals(5, company.getLowestSalaryStaff(5).size());
    }

    @Test
    void getLowestSalaryStaff2() {
        Company company = new Company();
        ArrayList<Employee> employees = new ArrayList<>();
        Manager e;
        for (int i = 50000; i > 0; i -= 1000) {
            e = new Manager();
            company.hire(e);
            employees.add(e);
        }
        Collections.sort(employees);

        assertEquals(employees, company.getLowestSalaryStaff(10000));
    }

    @Test
    void getLowestSalaryStaff3() {
        Company company = new Company();
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> company.getLowestSalaryStaff(-1));
        assertEquals(IllegalArgumentException.class, thrown.getClass());
    }

    @Test
    void getLowestSalaryStaff4() {
        Company company = new Company();
        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 1000; i < 50_000; i += 1000) {
            Manager e = new Manager();
            company.hire(e);
            employees.add(e);
        }
        Collections.sort(employees);
        assertEquals(employees.subList(0, 10), company.getLowestSalaryStaff(10));
    }

    @Test
    void getLowestSalaryStaff5() {
        Company company = new Company();
        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 1000; i < 50_000; i += 1000) {
            Manager m = new Manager();
            company.hire(m);
            employees.add(m);
        }
        for (Employee emp : company.getEmployees()) {
            if (Math.random() > 0.5) {
                company.fire(emp);
                employees.remove(emp);
            }
        }
        Collections.sort(employees);
        assertEquals(employees.subList(0, 10), company.getLowestSalaryStaff(10));
    }

    @Test
    void salaryCalcsTest() {
        Company c = new Company();
        TopManager tm = new TopManager();
        c.hire(tm);
        ArrayList<Employee> emps = new ArrayList<>();
        while (c.getIncome().compareTo(TopManager.BIG_INCOME) < 0) {
            Manager m = new Manager();
            c.hire(m);
            emps.add(m);
        }
        BigDecimal expected = TopManager.getSALARY().multiply(BigDecimal.valueOf(1 + TopManager.getTopManagerBonus()));
        assertEquals(0, tm.getMonthSalary().compareTo(expected));
        emps.forEach(c::fire);
        assertEquals(TopManager.getSALARY(), tm.getMonthSalary());
    }

    @Test
    void skillBoxTask() {
        Company c = new Company();

        ArrayList<Employee> employees = new ArrayList<>();

        //		наймите в компанию:
        //		180 операторов Operator,
        for (int i = 0; i < 180; i++) employees.add(new Operator());
        //		80 менеджеров по продажам Manager,
        for (int i = 0; i < 80; i++) employees.add(new Manager());
        //		10 топ менеджеров TopManager
        for (int i = 0; i < 10; i++) employees.add(new TopManager());

        assertTrue(c.hireAll(employees));

        int numberOfLinesInTop = 10;
        int numberOfLinesInLowest = 30;

        System.out.println("Список " + numberOfLinesInTop + " зарплат по убыванию:");
        printList(c.getTopSalaryStaff(numberOfLinesInTop));
        System.out.println("Список " + numberOfLinesInLowest + " зарплат по возрастанию:");
        printList(c.getLowestSalaryStaff(numberOfLinesInLowest));
        //Увольте 50% сотрудников
        for (Employee employee : c.getEmployees()) {
            if (Math.random() > 0.5) assertTrue(c.fire(employee));
        }
        System.out.println("После сокращения");
        System.out.println("Список " + numberOfLinesInTop + " зарплат по убыванию:");
        printList(c.getTopSalaryStaff(numberOfLinesInTop));
        System.out.println("Список " + numberOfLinesInLowest + " зарплат по возрастанию:");
        printList(c.getLowestSalaryStaff(numberOfLinesInLowest));
    }

    private void printList(List<Employee> e) {
        int count = 1;
        for (Employee employee : e) {
            System.out.println(count++ + ". " + employee);
        }
    }
}