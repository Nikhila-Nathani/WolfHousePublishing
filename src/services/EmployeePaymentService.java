package services;

import constants.Constants;
import entity.Employee;
import entity.EmployeePayment;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EmployeePaymentService {

    private static final String PAYMENT_ENTRY_FOR_EMPLOYEE = "INSERT INTO EMPLOYEE_PAYMENT(TRANSACTION_ID, EMPLOYEE_ID) VALUES (?,?)";

    public boolean createPaymentEntryForEmployee(EmployeePayment employeePayment){
        Connection connection = null;
        boolean flag = false;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(PAYMENT_ENTRY_FOR_EMPLOYEE);
            preparedStatement.setInt(1,employeePayment.getTransaction().getTrasactionId());
            preparedStatement.setInt(2,employeePayment.getEmployee().getEmployeeId());
            int result = preparedStatement.executeUpdate();
            flag = result == 1? true:false;
        }catch (Exception e){
            if(connection == null){
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return  flag;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return  flag;
        }
    }
}
