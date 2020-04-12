package controllers;

import entity.Contract;
import entity.Employee;
import entity.EmployeePayment;
import entity.Order;
import services.TransactionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionController {

    private TransactionService transactionService;

    public TransactionController(){
        transactionService = new TransactionService();
    }

    public List<Contract> createTransactionsForTemporaryEmployees(List<Contract> contracts){
        for(Contract c : contracts){
            int id = transactionService.createTransactionsForTemperoryEmployee(c);
            if(id!=-1){
                c.setTransactionId(id);
            }
        }
        return contracts;
    }

    public List<EmployeePayment> createTransactionForEmployee(List<Employee> employees){
        List<EmployeePayment> employeePayments = new ArrayList<>();

        for(Employee e : employees){
            employeePayments.add(transactionService.createTransactionForEmployee(e));
        }
        return  employeePayments;
    }

    public int createTransactionForOrder(Order order) {
        return transactionService.createTransactionForOrder(order);
    }
}
