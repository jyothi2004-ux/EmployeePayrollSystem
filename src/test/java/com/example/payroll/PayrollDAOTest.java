package com.example.payroll;
import com.example.payroll.dao.PayrollDAO;
import com.example.payroll.model.Employee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PayrollDAOTest {
    @Test
    void testAddEmployee() {
        PayrollDAO dao = new PayrollDAO();
        Employee e = new Employee("TestUser", "test@user.com", "HR", "2025-11-02");
        assertDoesNotThrow(() -> dao.addEmployee(e));
    }
}