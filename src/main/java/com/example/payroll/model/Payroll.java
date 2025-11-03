package com.example.payroll.model;

public class Payroll {
    private int id;
    private int employeeId;
    private double basicSalary;
    private double bonus;
    private double deductions;
    private double netSalary;
    private String payDate;

    public Payroll(int employeeId, double basicSalary, double bonus, double deductions, double netSalary, String payDate) {
        this.employeeId = employeeId;
        this.basicSalary = basicSalary;
        this.bonus = bonus;
        this.deductions = deductions;
        this.netSalary = netSalary;
        this.payDate = payDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public double getBonus() {
        return bonus;
    }

    public double getDeductions() {
        return deductions;
    }

    public double getNetSalary() {
        return netSalary;
    }

    public String getPayDate() {
        return payDate;
    }

    @Override
    public String toString() {
        return "Employee ID: " + employeeId +
                ", Basic: " + basicSalary +
                ", Bonus: " + bonus +
                ", Deductions: " + deductions +
                ", Net: " + netSalary +
                ", Pay Date: " + payDate;
    }
}