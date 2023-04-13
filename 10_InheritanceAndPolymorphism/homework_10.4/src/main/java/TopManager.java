package main.java;

/**
 * Created by a.sosnina on 11/14/2021.
 */
public class TopManager implements Employee {
    private double salaryFixPart;
    private double monthSalary;
    private Company company;

    public TopManager(Company company, double salaryFixPart) {
        this.company = company;
        this.salaryFixPart = salaryFixPart;
        setMonthSalary();
    }

    private void setMonthSalary() {
        if(company.getIncome() > 10000000) monthSalary =  salaryFixPart * 2.5;
        else monthSalary = salaryFixPart;
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
