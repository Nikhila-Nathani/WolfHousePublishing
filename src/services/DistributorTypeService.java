package services;

import constants.Constants;
import entity.DistributorType;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DistributorTypeService {
    private static final String GET_ALL_DISTRIBUTOR_TYPES = "SELECT * FROM DISTRIBUTOR_TYPE";
    private static final String CREATE_DISTRIBUTOR_TYPE = "INSERT INTO DISTRIBUTOR_TYPE(NAME) VALUES(?)";

    public List<Object> getAllDistributorTypes(){
        List<Object> distributorTypes = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_DISTRIBUTOR_TYPES);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    distributorTypes.add(new DistributorType(resultSet.getInt("ID"),resultSet.getString("NAME")));
                }
            }
        }catch(Exception e){
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            return distributorTypes;
        }finally {
            try{
                DatabaseUtility.closeconnection();
                return distributorTypes;
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return distributorTypes;
        }
    }

    public boolean createDistributorType(DistributorType distributorType){
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_DISTRIBUTOR_TYPE);
            preparedStatement.setString(1,distributorType.getDistributorTypeName());
            int result = preparedStatement.executeUpdate();
            flag = result == 1 ? true: false;
        }catch(Exception e) {
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return flag;
        }
    }


}


