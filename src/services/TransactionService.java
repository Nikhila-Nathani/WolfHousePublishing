package services;

import constants.Constants;
import entity.*;
import utility.DatabaseUtility;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TransactionService {

    private static final String CREATE_TRANSACTION =  "INSERT INTO TRANSACTIONS(DATE_OF_TRANS, AMOUNT) VALUES (?,?)" ;

    public Integer createTransactionsForTemperoryEmployee(Contract contract){

        Connection connection = null;
        Integer id = -1;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TRANSACTION, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(2,contract.getPay());
            LocalDate localDate = LocalDate.now();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            preparedStatement.setDate(1, Date.valueOf(dtf.format(localDate)));
            int result = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                id = resultSet.getInt(1);
            }
        }catch(Exception e){
            if(connection==null){
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }

            return id;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return id;
        }

    }

    public EmployeePayment createTransactionForEmployee(Employee employee){
        int transactionId = -1;
        EmployeePayment ep = new EmployeePayment();
        ep.setEmployee(employee);
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            //System.out.println("Current e : "+employee.toString());
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TRANSACTION,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(2,employee.getPay());
            LocalDate localDate = LocalDate.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            Date dateOfTransaction = Date.valueOf(dtf.format(localDate));
            preparedStatement.setDate(1, dateOfTransaction);
            //System.out.println("PS : "+preparedStatement.toString());
            int result = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if(resultSet.next()){
                transactionId = resultSet.getInt(1);
            }
            Transaction transaction = new Transaction(transactionId,dateOfTransaction,employee.getPay());
            ep.setTransaction(transaction);
        }catch(Exception e){
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            return ep;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return ep;
        }
    }


    public int createTransactionForOrder(Order order) {

        Connection connection = null;
        Integer id = -1;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TRANSACTION, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(2,order.getPrice()+order.getShippingCost());
            LocalDate localDate = LocalDate.now();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            preparedStatement.setDate(1, Date.valueOf(dtf.format(localDate)));
            int result = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                id = resultSet.getInt(1);
            }
        }catch(Exception e){
            if(connection==null){
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }

            return id;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return id;
        }

    }
}
