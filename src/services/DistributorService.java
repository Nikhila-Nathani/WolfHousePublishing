package services;

import constants.Constants;
import entity.Distributor;
import entity.DistributorType;
import utility.DatabaseUtility;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DistributorService {
    private static final String GET_ALL_DISTRIBUTORS = "SELECT D1.ID AS ID, D1.NAME AS D_NAME, D1.PHONE_NO AS PHONE_NO, " +
            "D1.BALANCE AS BALANCE, D1.ACTIVE_STATUS AS ACTIVE_STATUS, D2.ID AS DT_ID, D2.NAME AS DT_NAME " +
            "FROM DISTRIBUTOR D1, DISTRIBUTOR_TYPE D2 " +
            "WHERE D1.DISTRIBUTOR_TYPE = D2.ID";

    private static final String CREATE_DISTRIBUTOR = "INSERT INTO DISTRIBUTOR(NAME,PHONE_NO,BALANCE,ACTIVE_STATUS,DISTRIBUTOR_TYPE) " +
            "VALUES(?,?,?,?,?)";
    private static final String UPDATE_DISTRIBUTOR = "UPDATE  DISTRIBUTOR SET NAME = ?, PHONE_NO = ?, ACTIVE_STATUS = ?, DISTRIBUTOR_TYPE = ? WHERE ID = ?";

    private static final String DELETE_DISTRIBUTOR = "DELETE FROM DISTRIBUTOR WHERE ID =?";

    private static final String TOTAL_DISTRIBUTOR = "SELECT COUNT(ID) AS TOTAL FROM DISTRIBUTOR WHERE  ACTIVE_STATUS = 'ACTIVE'";
    private static final String UPDATE_DISTRIBUTOR_BALANCE = "UPDATE DISTRIBUTOR SET BALANCE =? WHERE ID = ?";
    private static final String GET_DISTRIBUTOR_BY_ID = "SELECT DT.ID AS DTID, DT.NAME AS DTNAME, D.ID AS DID, D.BALANCE AS BALANCE, D.DISTRIBUTOR_TYPE AS DTYPE, D.NAME AS NAME, D.PHONE_NO AS PHONE, D.ACTIVE_STATUS AS ACTIVE_STATUS FROM DISTRIBUTOR D, DISTRIBUTOR_TYPE AS DT WHERE D.ID = ? AND D.DISTRIBUTOR_TYPE = DT.ID";


    public List<Object> getAllDistributors(){
        List<Object> distributors = new ArrayList<>();
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

    public int createDistributorAndGetId(Distributor distributor){
        int distributorId = -1;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_DISTRIBUTOR, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,distributor.getDistributorName());
            preparedStatement.setLong(2,distributor.getPhoneNumber());
            preparedStatement.setInt(3,distributor.getBalance());
            preparedStatement.setString(4,distributor.getActiveStatus());
            preparedStatement.setInt(5, distributor.getDistributorType().getDistributorTypeId());
            int result = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                distributorId = resultSet.getInt(1);
            }

        }catch (Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }

            return distributorId;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return distributorId;
        }
    }


    public boolean updateDistributor(Distributor currentDistributor) {
        Connection connection = null;
        boolean flag = false;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DISTRIBUTOR);

            preparedStatement.setString(1,currentDistributor.getDistributorName());
            preparedStatement.setLong(2,currentDistributor.getPhoneNumber());
            preparedStatement.setString(3,currentDistributor.getActiveStatus());
            preparedStatement.setInt(4,currentDistributor.getDistributorType().getDistributorTypeId());
            preparedStatement.setInt(5,currentDistributor.getDistributorId());
            int result = preparedStatement.executeUpdate();
            flag = result ==1 ? true: false ;
        } catch(Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return flag;
        } finally{
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return flag;
        }
    }

    public boolean deleteDistributor(Distributor distributor) {
        Connection connection = null;
        boolean flag = false;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_DISTRIBUTOR);
            preparedStatement.setInt(1,distributor.getDistributorId());
            System.out.println(preparedStatement.toString());
            int result = preparedStatement.executeUpdate();
            flag = result ==1 ? true: false ;
        } catch(Exception e){
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            return flag;
        } finally{
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return flag;
        }
    }

    public int getTotalDistributors() {
        Connection connection = null;
        int total = -1;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(TOTAL_DISTRIBUTOR);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                total = resultSet.getInt("TOTAL");
            }
        } catch(Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return total;
        } finally{
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return total;
        }
    }

    public boolean updateBalance(Distributor currentDistributor) {
        Connection connection = null;
        boolean flag = false;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DISTRIBUTOR_BALANCE);
            preparedStatement.setInt(1,currentDistributor.getBalance());
            preparedStatement.setInt(2,currentDistributor.getDistributorId());
            int result = preparedStatement.executeUpdate();
            flag = result ==1 ? true: false ;
        } catch(Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return flag;
        } finally{
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return flag;
        }
    }

    public Distributor getDistributorById(int distributorId) {
        Connection connection = null;
        Distributor distributor = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_DISTRIBUTOR_BY_ID);
            preparedStatement.setInt(1,distributorId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                distributor = new Distributor(
                        resultSet.getInt("DID"),
                        resultSet.getString("NAME"),
                        resultSet.getLong("PHONE"),
                        resultSet.getInt("BALANCE"),
                        resultSet.getString("ACTIVE_STATUS"),
                        new DistributorType(
                                resultSet.getInt("DTID"),
                                resultSet.getString("DTNAME")
                        )
                );
            }
        } catch(Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return distributor;
        } finally{
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return distributor;
        }
    }
}
