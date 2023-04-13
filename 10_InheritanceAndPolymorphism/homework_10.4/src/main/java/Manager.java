package main.java;

/**
 * Created by a.sosnina on 11/14/2021.
 */
public class Manager implements Employee {
    private double salaryFixPart;
    private double monthSalary;
    private Company company;

    public Manager(Company company, double salaryFixPart) {
        this.company = company;
        this.salaryFixPart = salaryFixPart;
        setMonthSalary();
    }

    public Manager(double salaryFixPart) {
        this.salaryFixPart = salaryFixPart;
    }

    public void setSalaryFixPart(double salary) {
        this.salaryFixPart = salary;
    }

    public double getGeneratedProfit () {
        return Math.random() * (140000 - 115000) + 115000;
    }
    private void setMonthSalary() {
        monthSalary =  salaryFixPart + Math.round(getGeneratedProfit() * 0.05);
    }

    @Override
    public double getMonthSalary() {
        return monthSalary;
    }

    @Override
    public int compareTo(Employee o) {
        return Double.compare(o.getMonthSalary(), monthSalary);
    }

}
