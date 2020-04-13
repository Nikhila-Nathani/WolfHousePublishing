package services;

import constants.Constants;
import entity.Editor;
import entity.Employee;
import utility.DatabaseUtility;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EditorService {
    private static final String GET_ALL_EDITORS = "SELECT E2.ID AS ID, E2.NAME AS NAME, E2.PAY AS PAY, E2.PERIODICITY AS PERIOD FROM EDITOR E1, EMPLOYEE E2 WHERE E2.ID = E1.EMPLOYEE_ID";

    public List<Object> getAllEditors(){
        List<Object> editors = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_EDITORS);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while (resultSet.next()){
                    editors.add(new Editor(new Employee(resultSet.getInt("ID"),resultSet.getString("NAME"),resultSet.getFloat("PAY"),resultSet.getInt("PERIOD"))));
                }
            }
        }catch (Exception e) {
            if(connection== null ){
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }

            return editors;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return editors;
        }
    }
}
