package services;

import constants.Constants;
import entity.Order;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    private static final String DELETE_ORDER = "DELETE FROM ORDERS WHERE ID = ?";
    private static final String CREATE_ORDER = "INSERT INTO ORDERS (PRICE, ORDER_DATE, SHIPPING_COST, DELIVERY_DATE) VALUES (?,?,?,?)";

    private static final String GET_ALL_ORDERS_FOR_PAYMENT = "SELECT O.TRANSACTION_ID AS TID, O.ID AS ID, O.PRICE AS PRICE, O.SHIPPING_COST AS SC, O.DELIVERY_DATE AS DD, O.ORDER_DATE AS OD FROM ORDERS O WHERE ID NOT IN (SELECT OP.ORDER_ID FROM ORDER_PAYMENT OP)";

    public boolean deleteOrder(Order order){
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ORDER);
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

    public Integer createOrder(Order order) {
        Integer orderId = -1;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setFloat(1,order.getPrice());
            preparedStatement.setDate(2,order.getOrderDate());
            preparedStatement.setFloat(3,order.getShippingCost());
            preparedStatement.setDate(4,order.getDeliveryDate());
            int result = preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                orderId = resultSet.getInt(1);
            }
        }catch (Exception e){
            if(connection!=null){
                System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return orderId;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return orderId;
        }
    }

    public List<Object> getAllOrdersForPayment() {
        List<Object> orders = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS_FOR_PAYMENT);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    orders.add(new Order(resultSet.getInt("ID"),resultSet.getFloat("PRICE"),
                    resultSet.getDate("OD"),resultSet.getFloat("SC"),resultSet.getDate("DD"),resultSet.getInt("TID")));
                }
            }
        }catch (Exception e){
            if(connection == null){
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return orders;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return orders;
        }
    }
}
