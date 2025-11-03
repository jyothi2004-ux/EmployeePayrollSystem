package com.example.payroll;

import com.example.payroll.model.Employee;
import com.example.payroll.model.Payroll;
import com.example.payroll.service.PayrollService;
import com.example.payroll.exception.PayrollException;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PayrollService service = new PayrollService();

        while (true) {
            System.out.println("\n===== Employee Payroll System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Add Payroll");
            System.out.println("4. View All Payroll Records");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();
                        System.out.print("Enter Department: ");
                        String dept = sc.nextLine();
                        System.out.print("Enter Hire Date (YYYY-MM-DD): ");
                        String date = sc.nextLine();

                        service.addEmployee(new Employee(name, email, dept, date));
                        System.out.println(" Employee added successfully!");
                    }

                    case 2 -> {
                        List<Employee> employees = service.viewAllEmployees();
                        if (employees.isEmpty()) {
                            System.out.println(" No employees found.");
                        } else {
                            System.out.println("\n--- Employee List ---");
                            for (Employee e : employees) {
                                System.out.println(e.getId() + " | " + e.getName() + " | " + e.getEmail()
                                        + " | " + e.getDepartment() + " | " + e.getHireDate());
                            }
                        }
                    }

                    case 3 -> {
                        System.out.print("Enter Employee ID: ");
                        int empId = sc.nextInt();
                        System.out.print("Enter Basic Salary: ");
                        double basic = sc.nextDouble();
                        System.out.print("Enter Bonus: ");
                        double bonus = sc.nextDouble();
                        System.out.print("Enter Deductions: ");
                        double deductions = sc.nextDouble();
                        sc.nextLine();


                        System.out.print("Enter Pay Date (YYYY-MM-DD): ");
                        String payDate = sc.nextLine();

                        double netSalary = basic + bonus - deductions;

                        Payroll payroll = new Payroll(empId, basic, bonus, deductions, netSalary, payDate);
                        service.addPayroll(payroll);

                        System.out.println(" Payroll added successfully for Employee ID: " + empId);
                        System.out.println(" Net Salary = " + netSalary);
                    }

                    case 4 -> {
                        List<Payroll> payrolls = service.viewAllPayrolls();
                        if (payrolls.isEmpty()) {
                            System.out.println(" No payroll records found.");
                        } else {
                            System.out.println("\n--- Payroll Records ---");
                            for (Payroll p : payrolls) {
                                System.out.println("Employee ID: " + p.getEmployeeId() +
                                        " | Basic: " + p.getBasicSalary() +
                                        " | Bonus: " + p.getBonus() +
                                        " | Deductions: " + p.getDeductions() +
                                        " | Net: " + p.getNetSalary() +
                                        " | Pay Date: " + p.getPayDate());
                            }
                        }
                    }

                    case 5 -> {
                        System.out.println(" Exiting... Thank you for using Employee Payroll System!");
                        System.exit(0);
                    }


                    default -> System.out.println(" Invalid choice! Please try again.");
                }
            } catch (PayrollException e) {
                System.out.println(" Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(" Unexpected Error: " + e.getMessage());
            }
        }
    }
}