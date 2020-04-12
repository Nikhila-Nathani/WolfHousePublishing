package services;

import constants.Constants;
import entity.Employee;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EmployeeService {

    private static final String GET_PERMANENT_EMPLOYEES_FOR_PAYMENT =
            "SELECT E.ID AS ID, E.NAME AS NAME, E.PAY AS PAY, E.PERIODICITY AS PER FROM EMPLOYEE E WHERE PERIODICITY <= ? AND PAY IS NOT NULL AND E.ID NOT IN (\n" +
                    "SELECT EP.EMPLOYEE_ID FROM EMPLOYEE_PAYMENT EP WHERE EP.TRANSACTION_ID IN(\n" +
                    "SELECT T.ID FROM TRANSACTIONS T WHERE T.ID IN (\n" +
                    "    SELECT EP.TRANSACTION_ID FROM EMPLOYEE_PAYMENT EP\n" +
                    "    ) AND DAY(T.DATE_OF_TRANS) <= ? AND MONTH(DATE_OF_TRANS) = ? AND YEAR(DATE_OF_TRANS)=?))";

    public List<Employee> getPermanentEmployeesForPayment(Date date) {
        List<Employee> employees = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PERMANENT_EMPLOYEES_FOR_PAYMENT);
            String d = date.toString();
            String d1[] = d.split("-");
            int currentMonth = Integer.parseInt(d1[1]);
            int currentYear = Integer.parseInt(d1[0]);
            int day = Integer.parseInt(d1[2]);

            preparedStatement.setInt(1,day);
            preparedStatement.setInt(2,day);
            preparedStatement.setInt(3,currentMonth);
            preparedStatement.setInt(4,currentYear);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet!=null){
                while(resultSet.next()){
                    Employee e = new Employee(
                            resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getFloat("PAY"),resultSet.getInt("PER"));
                    employees.add(e);
                }
            }
        }catch(Exception e){
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            return  employees;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return employees;
        }
    }
}
