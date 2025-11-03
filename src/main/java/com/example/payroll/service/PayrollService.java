package com.example.payroll.service;

import com.example.payroll.dao.PayrollDAO;
import com.example.payroll.exception.PayrollException;
import com.example.payroll.model.Employee;
import com.example.payroll.model.Payroll;
import java.util.List;

public class PayrollService {
    private PayrollDAO dao = new PayrollDAO();


    public void addEmployee(Employee emp) throws PayrollException {
        dao.addEmployee(emp);
    }


    public List<Employee> viewAllEmployees() throws PayrollException {
        return dao.getAllEmployees();
    }


    public void addPayroll(Payroll payroll) throws PayrollException {
        dao.addPayroll(payroll);
    }


    public List<Payroll> viewAllPayrolls() throws PayrollException {
        return dao.getAllPayrolls();
    }
}