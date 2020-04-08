package services;

import constants.Constants;
import entity.Author;
import entity.Employee;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AuthorService {

    private static final String GET_ALL_AUTHORS = "SELECT A.EMPLOYEE_ID AS E_ID, E.NAME AS NAME, E.PAY AS PAY," +
            "E.PERIODICITY AS PERIODICITY FROM AUTHOR A, EMPLOYEE E WHERE A.EMPLOYEE_ID = E.ID";

    public List<Object> getAllAUthors(){
        List<Object> authors = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_AUTHORS);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    Employee e = new Employee(resultSet.getInt("E_ID"),resultSet.getString("NAME"),
                            resultSet.getFloat("PAY"),resultSet.getInt("PERIODICITY"));
                    authors.add(new Author(e));
                }
            }
        }catch(Exception e){
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            return authors;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return authors;
        }
    }
}
