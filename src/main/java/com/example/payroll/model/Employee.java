package com.example.payroll.model;

public class Employee {
    private int id;
    private String name;
    private String email;
    private String department;
    private String hireDate;

    public Employee(int id, String name, String email, String department, String hireDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.hireDate = hireDate;
    }

    public Employee(String name, String email, String department, String hireDate) {
        this.name = name;
        this.email = email;
        this.department = department;
        this.hireDate = hireDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public String getHireDate() {
        return hireDate;
    }

    @Override
    public String toString() {
        return id + " | " + name + " | " + email + " | " + department + " | " + hireDate;
    }
}