package com.example.payroll.dao;

import com.example.payroll.exception.PayrollException;
import com.example.payroll.model.Employee;
import com.example.payroll.model.Payroll;
import com.example.payroll.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayrollDAO {


    public void addEmployee(Employee emp) throws PayrollException {
        String query = "INSERT INTO employees (name, email, department, hire_date) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, emp.getName());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getDepartment());
            ps.setDate(4, Date.valueOf(emp.getHireDate()));

            ps.executeUpdate();
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new PayrollException("Employee with this email already exists!");
        } catch (Exception e) {
            throw new PayrollException("Error adding employee: " + e.getMessage());
        }
    }


    public List<Employee> getAllEmployees() throws PayrollException {
        List<Employee> list = new ArrayList<>();
        String query = "SELECT * FROM employees";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Employee e = new Employee(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("department"),
                        rs.getString("hire_date")
                );
                list.add(e);
            }
        } catch (Exception e) {
            throw new PayrollException("Error fetching employees: " + e.getMessage());
        }
        return list;
    }


    public void addPayroll(Payroll payroll) throws PayrollException {
        String checkQuery = "SELECT * FROM payrolls WHERE employee_id = ? AND pay_date = ?";
        String insertQuery = "INSERT INTO payrolls (employee_id, basic_salary, bonus, deductions, net_salary, pay_date) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement checkStmt = con.prepareStatement(checkQuery)) {

            checkStmt.setInt(1, payroll.getEmployeeId());
            checkStmt.setDate(2, Date.valueOf(payroll.getPayDate()));
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                throw new PayrollException("Payroll for this employee on the same date already exists!");
            }

            try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
                ps.setInt(1, payroll.getEmployeeId());
                ps.setDouble(2, payroll.getBasicSalary());
                ps.setDouble(3, payroll.getBonus());
                ps.setDouble(4, payroll.getDeductions());
                ps.setDouble(5, payroll.getNetSalary());
                ps.setDate(6, Date.valueOf(payroll.getPayDate()));

                ps.executeUpdate();
            }

        } catch (PayrollException e) {
            throw e;
        } catch (Exception e) {
            throw new PayrollException("Error adding payroll: " + e.getMessage());
        }
    }


    public List<Payroll> getAllPayrolls() throws PayrollException {
        List<Payroll> list = new ArrayList<>();
        String query = "SELECT * FROM payrolls ORDER BY employee_id, pay_date";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Payroll p = new Payroll(
                        rs.getInt("employee_id"),
                        rs.getDouble("basic_salary"),
                        rs.getDouble("bonus"),
                        rs.getDouble("deductions"),
                        rs.getDouble("net_salary"),
                        rs.getString("pay_date")
                );
                list.add(p);
            }
        } catch (Exception e) {
            throw new PayrollException("Error fetching payrolls: " + e.getMessage());
        }
        return list;
    }
}