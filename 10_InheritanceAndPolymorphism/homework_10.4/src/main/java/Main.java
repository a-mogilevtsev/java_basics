package main.java;

import java.util.List;

/**
 * Created by a.sosnina on 11/14/2021.
 */
public class Main {

    public static void main(String[] args) {
        Company company = new Company();
        for(int i = 0; i < 180; i++) {
            company.hire(new Operator(company, 45000));
        }
        for(int i = 0; i < 80; i++) {
            company.hire(new Manager(company, 50000));
        }
        for(int i = 0; i < 10; i++) {
            company.hire(new TopManager(company, 85000));
        }
        List<Employee> topSalaryEmployees = company.getTopSalaryStaff(10);
        List<Employee> lowSalaryEmployees = company.getLowestSalaryStaff(30);
        System.out.println("10 Сотрудников с наибольшими зарплатами");
        printEmployeesInfo(topSalaryEmployees);
        System.out.println("30 Сотрудников с наименьшими зарплатами");
        printEmployeesInfo(lowSalaryEmployees);

        //увольнение части сотрудников
        for(int i = 0; i < company.getEmployees().size(); i = i + 2) {
            company.getEmployees().remove(i);
        }

        topSalaryEmployees = company.getTopSalaryStaff(10);
        lowSalaryEmployees = company.getLowestSalaryStaff(30);

        System.out.println("10 Сотрудников с наибольшими зарплатами после увольнения части сотрудников");
        printEmployeesInfo(topSalaryEmployees);
        System.out.println("30 Сотрудников с наименьшими зарплатами после увольнения части сотрудников");
        printEmployeesInfo(lowSalaryEmployees);
    }

    public static void printEmployeesInfo (List<Employee> employees) {
        for(Employee employee:employees) {
            System.out.println(employee.getMonthSalary());
        }
    }
}
