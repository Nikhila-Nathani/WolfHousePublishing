package services;

import constants.Constants;
import entity.Distributor;
import entity.Order;
import entity.OrderPlaced;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderPlacedService {
    private static final String GET_ORDER_IDS_FOR_DISTRIBUTOR = "SELECT ORDER_ID FROM ORDERS_PLACED WHERE DISTRIBUTOR_ID = ?";
    private static final String DELETE_ORDER_PLACED_FOR_DISTRIBUTOR = "DELETE FROM ORDERS_PLACED WHERE DISTRIBUTOR_ID = ?";
    private static final String CREATE_ORDER_PLACED = "INSERT  INTO ORDERS_PLACED (DISTRIBUTOR_ID, ORDER_ID, LOCATION) VALUES(?,?,?)";
    private static final String GET_DISTRIBUTOR_ID = "SELECT DISTRIBUTOR_ID AS DID FROM ORDERS_PLACED WHERE ORDER_ID = ?";

    public List<Order> getOrdersForDistributor(Distributor distributor){
        List<Order> orderIds= new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_IDS_FOR_DISTRIBUTOR);
            preparedStatement.setInt(1,distributor.getDistributorId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    orderIds.add(new Order(resultSet.getInt("ORDER_ID")));
                }
            }
        }catch (Exception e){
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            return orderIds;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return orderIds;
        }
    }

    public boolean deleteOrdersPlacesForDistributor(Distributor distributor){
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_PLACED_FOR_DISTRIBUTOR);
            preparedStatement.setInt(1,distributor.getDistributorId());
            int result = preparedStatement.executeUpdate();
            if(result>=0){flag = true;}
        }catch (Exception e){
            if(connection!=null){
                System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
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

    public boolean createOrderPlaced(OrderPlaced orderPlaced) {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER_PLACED);
            preparedStatement.setInt(1,orderPlaced.getDistributorId());
            preparedStatement.setInt(2,orderPlaced.getOrderId());
            preparedStatement.setString(3,orderPlaced.getLocation());

            int result = preparedStatement.executeUpdate();
            flag = result == 1?true:false;
        }catch (Exception e){
            if(connection!=null){
                System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
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

    public int getDistributorIdForOrder(Order order) {
        int distributorId = -1;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_DISTRIBUTOR_ID);
            preparedStatement.setInt(1,order.getOrderId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                distributorId = resultSet.getInt("DID");
            }
        }catch (Exception e){
            if(connection!=null){
                System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
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

    public void updateLocation(Order order, String location) {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_DISTRIBUTOR_ID);
            preparedStatement.setString(1,location);
            preparedStatement.setInt(2,order.getOrderId());
            int result = preparedStatement.executeUpdate();
            flag = result == 1? true :false;
        }catch (Exception e){
            if(connection!=null){
                System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }

        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
        }
    }
}
