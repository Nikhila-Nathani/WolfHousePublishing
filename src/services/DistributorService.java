package services;

import constants.Constants;
import entity.Distributor;
import entity.DistributorType;
import utility.DatabaseUtility;

import javax.xml.crypto.Data;
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

    private static final String CREATE_DISTRIBUTOR = "INSERT INTO DISTRIBUTOR(NAME,PHONE_NO,BALANCE,ACTIVE_STATUS,DISTRIBUTOR_TYPE) " +
            "VALUES(?,?,?,?,?)";

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
                            resultSet.getLong("PHONE_NO"), resultSet.getInt("BALANCE"),
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

    public boolean createDistributor(Distributor distributor){
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_DISTRIBUTOR);
            preparedStatement.setString(1,distributor.getDistributorName());
            preparedStatement.setLong(2,distributor.getPhoneNumber());
            preparedStatement.setInt(3,distributor.getBalance());
            preparedStatement.setString(4,distributor.getActiveStatus());
            preparedStatement.setInt(5, distributor.getDistributorType().getDistributorTypeId());
            int result = preparedStatement.executeUpdate();
            flag = result ==1 ? true: false;

        }catch (Exception e){
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            return flag;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return flag;
        }
    }


}
