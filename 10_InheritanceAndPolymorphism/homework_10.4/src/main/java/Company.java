package main.java;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by a.sosnina on 11/14/2021.
 */
public class Company {
    private List<Employee> employees = new ArrayList<>();

    public Company() {
    }

    public void hire(Employee employee) {
        employees.add(employee);
    }

    public List <Employee> getEmployees() {
    return employees;
    }

    public void hireAll (List<Employee> fewEmployees) {
        employees.addAll(fewEmployees);
    }

    List<Employee> getTopSalaryStaff(int count) {
        List <Employee> employeesWithTopSalary = new ArrayList<>();
        if(count < employees.size()-1 && count > 0) {
            for (int i = 0; i < count; i++) {
                Collections.sort(employees, ((o1, o2) -> {
                    return Double.compare(o2.getMonthSalary(), o1.getMonthSalary());
                }));
                employeesWithTopSalary.add(employees.get(i));
            }

        }
        return employeesWithTopSalary;
    }

    public void fire(Employee employee) {
        if(employees.contains(employee)) employees.remove(employee);
    }

    List<Employee> getLowestSalaryStaff(int count) {
        List<Employee> employeesWithLowSalary = new ArrayList<>();
        employees.sort(Comparator.comparingDouble(Employee::getMonthSalary));
        for(int i = 0; i < count; i++) {
            employeesWithLowSalary.add(employees.get(i));
        }
        return employeesWithLowSalary;
    }



    public double getIncome() {
        double income = 0;
        for(Employee employee:employees) {
            if(employee instanceof Manager) {
                income += ((Manager) employee).getGeneratedProfit();
            }
        }
        return income;
    }
}
