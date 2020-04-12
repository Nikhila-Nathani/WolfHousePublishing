package services;

import constants.Constants;
import entity.Order;
import entity.OrderContains;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderContainsService {

    private static final String DELETE_ORDER_CONTAINS_FOR_ORDER = "DELETE FROM ORDER_CONTAINS WHERE ORDER_ID = ?";
    private static final String CREATE_ORDER_CONTAINS_FOR_ORDER = "INSERT INTO ORDER_CONTAINS (order_id, publication_id, no_of_copies) VALUES(?,?,?)";

    public boolean deleteOrderContainsForOrder(Order order){
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER_CONTAINS_FOR_ORDER);
            preparedStatement.setInt(1,order.getOrderId());
            int result = preparedStatement.executeUpdate();
            flag = result == 0? false :true;
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

    public boolean createOrderContains(OrderContains orderContains){
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER_CONTAINS_FOR_ORDER);
            preparedStatement.setInt(1,orderContains.getOrderId());
            preparedStatement.setInt(2, orderContains.getPublication().getPublicationId());
            preparedStatement.setInt(3, orderContains.getNumberOfCopies());

            int result = preparedStatement.executeUpdate();
            flag = result ==1 ? true : false;
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
}
