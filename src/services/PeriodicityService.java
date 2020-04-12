package services;

import constants.Constants;
import entity.Periodicity;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PeriodicityService {

    private static final String GET_ALL_PERIODICITY = "SELECT ID, NAME FROM PERIODICITY";

    public List<Object> getAllPeriodicities(){
        List<Object> periodicities = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_PERIODICITY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    Integer id = resultSet.getInt("ID");
                    String periodicity = resultSet.getString("NAME");
                    periodicities.add(new Periodicity(id,periodicity));
                }
            }
        } catch (Exception e) {
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            return periodicities;
        } finally {
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return periodicities;
        }
    }
}
