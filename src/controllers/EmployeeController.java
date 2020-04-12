package controllers;

import entity.Employee;
import services.EmployeeService;

import java.sql.Date;
import java.util.List;

public class EmployeeController {
    private EmployeeService employeeService;


    public EmployeeController(){
        employeeService = new EmployeeService();
    }
    public List<Employee> getPermanentEmployeesForPayment(Date date) {
        return employeeService.getPermanentEmployeesForPayment(date);
    }
}
