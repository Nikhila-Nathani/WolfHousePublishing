package services;

import constants.Constants;
import entity.Distributor;
import entity.DistributorType;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DistributorService {
    private static final String GET_ALL_DISTRIBUTORS = "SELECT D1.ID AS ID, D1.NAME AS D_NAME, D1.PHONE_NO AS PHONE_NO, " +
            "D1.BALANCE AS BALANCE, D1.ACTIVE_STATUS AS ACTIVE_STATUS, D2.ID AS DT_ID, D2.NAME AS DT_NAME " +
            "FROM DISTRIBUTOR D1, DISTRIBUTOR_TYPE D2 " +
            "WHERE D1.DISTRIBUTOR_TYPE = D2.ID";

    public List<Distributor> getAllDistributors(){
        List<Distributor> distributors = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_DISTRIBUTORS);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    distributors.add(new Distributor(resultSet.getInt("ID"),resultSet.getString("D_NAME"),
                            resultSet.getInt("PHONE_NO"), resultSet.getFloat("BALANCE"),
                            resultSet.getString("ACTIVE_STATUS"),
                            new DistributorType(resultSet.getInt("DT_ID"),resultSet.getString("DT_NAME"))));
                }
            }
        }catch(Exception e){
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            return distributors;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return distributors;
        }
    }
}
