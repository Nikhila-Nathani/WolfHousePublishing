package services;

import constants.Constants;
import entity.Address;
import entity.Distributor;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AddressService {

    private static final String ADD_NEW_ADDRESS = "INSERT INTO ADDRESS(DISTRIBUTOR_ID, LOCATION, CONTACT_PERSON, CITY) VALUES(?,?,?,?)";
    private static final String DELETE_ADDRESS_FOR_DISTRIBUTOR ="DELETE FROM ADDRESS WHERE DISTRIBUTOR_ID = ?" ;
    private static final String GET_ADDRESS_FOR_DISTRIBUTOR = "SELECT A.DISTRIBUTOR_ID AS D_ID, A.LOCATION AS LOCATION,  A.CITY AS CITY, A.CONTACT_PERSON AS CP FROM ADDRESS A WHERE DISTRIBUTOR_ID = ?";
    private static final String UPDATE_ADDRESS_FOR_DISTRIBUTOR = "UPDATE ADDRESS SET LOCATION = ?, CITY =?, CONTACT_PERSON=? WHERE DISTRIBUTOR_ID=? AND LOCATION LIKE ? ";

    public boolean addAddressForDistributor(Address address){
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            DatabaseUtility.beginTransaction();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_ADDRESS);
            preparedStatement.setInt(1,address.getDistributorId());
            preparedStatement.setString(2,address.getLocation());
            preparedStatement.setString(3,address.getContactPerson());
            preparedStatement.setString(4,address.getCity());
            int result = preparedStatement.executeUpdate();
            if(result==1){
                connection.commit();
            }else{
                connection.rollback();
            }
            flag = result == 1?true:false;
            DatabaseUtility.endTransaction();
        }catch (Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
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

    public boolean deleteAddressForDistributor(Distributor distributor) {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADDRESS_FOR_DISTRIBUTOR);
            preparedStatement.setInt(1,distributor.getDistributorId());
//            System.out.println(preparedStatement.toString());
            int result = preparedStatement.executeUpdate();
            if(result>=0){
                flag = true;
            }
        }catch (Exception e){
            if(connection == null){
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }

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

    public List<Object> getAddressForDistributor(Distributor currentDistributor) {
        List<Object> addresses = new ArrayList<>();
        Connection connection = null;

        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ADDRESS_FOR_DISTRIBUTOR);
            preparedStatement.setInt(1,currentDistributor.getDistributorId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    addresses.add(new Address(resultSet.getInt("D_ID"), resultSet.getString("LOCATION"),
                            resultSet.getString("CITY"),resultSet.getString("CP")));
                }
            }

        }catch (Exception e){
            if(connection==null){
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }

            return addresses;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return addresses;
        }

    }
    public boolean updateAddressForDistributor(Address address, String oldLocation) {
        Connection connection = null;
        boolean flag = false;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADDRESS_FOR_DISTRIBUTOR);
            preparedStatement.setString(1,address.getLocation());
            preparedStatement.setString(2,address.getCity());
            preparedStatement.setString(3,address.getContactPerson());
            preparedStatement.setInt(4,address.getDistributorId());
            preparedStatement.setString(5, oldLocation);
//            System.out.println(preparedStatement.toString());
            int result = preparedStatement.executeUpdate();
            flag = result>=0?true:false;

        }catch (Exception e){
            if(connection==null){
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }

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
