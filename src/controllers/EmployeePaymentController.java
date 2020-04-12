package controllers;

import entity.EmployeePayment;
import services.EmployeePaymentService;

import java.util.List;

public class EmployeePaymentController {
    private EmployeePaymentService employeePaymentService;

    public EmployeePaymentController(){
        employeePaymentService = new EmployeePaymentService();
    }

    public EmployeePayment createPaymentEntryForEmployee(List<EmployeePayment> employeePayments){
        for(EmployeePayment ep : employeePayments){
            if(!employeePaymentService.createPaymentEntryForEmployee(ep)){
                return ep;
            }
        }
        return null;
    }
}
