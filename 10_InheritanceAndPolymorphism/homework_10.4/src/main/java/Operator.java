package main.java;

/**
 * Created by a.sosnina on 11/14/2021.
 */
public class Operator implements Employee {
    Company company;
    private double salaryFixPart;

    public Operator(Company company, double salaryFixPart) {
        this.salaryFixPart = salaryFixPart;
        this.company = company;
    }

    @Override
    public double getMonthSalary() {
        return salaryFixPart;
    }

    @Override
    public int compareTo(Employee o) {
        return Double.compare(o.getMonthSalary(), salaryFixPart);
    }
}
