package services;

import constants.Constants;
import entity.Order;
import entity.OrderPayment;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class OrderPaymentService {


    private static final String DELETE_TRANSACTION_FOR_ORDER = "DELETE FROM ORDER_PAYMENT WHERE ORDER_ID = ?";
    private static final String CREATE_ORDER_PAYMENT = "INSERT INTO ORDER_PAYMENT (TRANSACTION_ID, ORDER_ID) VALUES(?,?)";

    public boolean deleteTransactionForOrder(Order order){
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TRANSACTION_FOR_ORDER);
            preparedStatement.setInt(1,order.getOrderId());
            int result = preparedStatement.executeUpdate();
            if(result>=0){flag = true;}
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

    public boolean createOrderPayment(OrderPayment orderPayment) {
            boolean flag = false;
            Connection connection = null;
            try{
                connection = DatabaseUtility.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER_PAYMENT);
                preparedStatement.setInt(1,orderPayment.getTransaction().getTrasactionId());
                preparedStatement.setInt(2,orderPayment.getOrder().getOrderId());
                int result = preparedStatement.executeUpdate();
                flag = result == 1? true : false;

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
