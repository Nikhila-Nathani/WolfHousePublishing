package services;

import constants.Constants;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;

public class OrderService {

    private static final String GET_PUBLICATION_BY_ID = "SELECT * FROM PUBLICATION WHERE ID=?";

    private static final String CREATE_ORDER_CONTAINS = "INSERT INTO ORDER_CONTAINS(ORDER_ID, PUBLICATION_ID, NO_OF_COPIES) VALUES (?,?,?)";
    private static final String CREATE_ORDER = "INSERT INTO ORDERS (PRICE, ORDER_DATE, SHIPPING_COST, DELIVERY_DATE)\n" +
            "VALUES (?,?,?,?)";
    private static final String CREATE_ORDERS_PLACED = "INSERT INTO ORDERS_PLACED(DISTRIBUTOR_ID, ORDER_ID) VALUES (?,?)";
    private static final String GET_LAST_ID ="SELECT * FROM ORDERS WHERE ID=(SELECT LAST_INSERT_ID())";

    private static final String CREATE_TRANSACTION = "INSERT INTO TRANSACTIONS(DATE_OF_TRANS,AMOUNT) VALUES (?,?)";

    private static final String CREATE_ORDER_PAYMENT = "INSERT INTO ORDER_PAYMENT (TRANSACTION_ID, ORDER_ID) VALUES (?,?)";

    private static final String UPDATE_ORDER_UPON_TRANSACTION = "UPDATE ORDERS SET TRANSACTION_ID=? WHERE ID=?";

    private static final String UPDATE_BALANCE_ON_RECEIPT = "UPDATE DISTRIBUTOR SET BALANCE=BALANCE-? WHERE ID=?";

    private static final String UPDATE_BALANCE_ON_ORDER_CREATION="UPDATE DISTRIBUTOR SET BALANCE=BALANCE+? WHERE ID=?";




    public static boolean createOrder(HashMap<Integer, Integer> order, int distributor_id){
        boolean flag = false;
        Connection connection = null;
        int price = 0;
        try{
            connection = DatabaseUtility.getConnection();
            for (int i : order.keySet()) {
                PreparedStatement preparedStatement = connection.prepareStatement(GET_PUBLICATION_BY_ID);
                preparedStatement.setInt(1,i);
                ResultSet resultSet = preparedStatement.executeQuery();
                while(resultSet.next()) {
                    int p = resultSet.getInt("PRICE");
                    price = price + (p*(order.get(i)));
                }
            }


            LocalDate current_date = LocalDate.now();
            LocalDate shipping_date = current_date.plusDays(7);

            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ORDER);
            preparedStatement.setInt(1,price);
            preparedStatement.setDate(2, java.sql.Date.valueOf(current_date));
            preparedStatement.setInt(3,50);
            preparedStatement.setDate(4, java.sql.Date.valueOf(shipping_date));

            int result_1 = preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(GET_LAST_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            int order_id = -1;
            while(resultSet.next()) {
                order_id = resultSet.getInt("ID");
            }

            System.out.println(order_id);



            preparedStatement = connection.prepareStatement(CREATE_ORDERS_PLACED);
            preparedStatement.setInt(1,distributor_id);
            preparedStatement.setInt(2, order_id);

            int result_2 = preparedStatement.executeUpdate();

            for (int i : order.keySet()) {
                preparedStatement = connection.prepareStatement(CREATE_ORDER_CONTAINS);
                preparedStatement.setInt(1,order_id);
                preparedStatement.setInt(2,i);
                preparedStatement.setInt(3,order.get(i));

                int result_3 = preparedStatement.executeUpdate();
            }

            preparedStatement = connection.prepareStatement(UPDATE_BALANCE_ON_ORDER_CREATION);
            preparedStatement.setInt(1, price);
            preparedStatement.setInt(2, distributor_id);

            int result_4 = preparedStatement.executeUpdate();




        }catch(Exception e) {
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            //System.out.println(e);
        }

        finally {
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            System.out.println(price);
            return flag;
        }

    }

    public static boolean update_balance(int amount, int order_id, int distributor_id){
        boolean flag = false;
        Connection connection = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TRANSACTION);
            LocalDate current_date = LocalDate.now();
            preparedStatement.setDate(1, java.sql.Date.valueOf(current_date));
            preparedStatement.setInt(2,amount);

            int result_1 = preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(GET_LAST_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            int transaction_id = -1;
            while(resultSet.next()) {
                transaction_id = resultSet.getInt("ID");
            }

            System.out.println(transaction_id);

            preparedStatement = connection.prepareStatement(CREATE_ORDER_PAYMENT);
            preparedStatement.setInt(1, transaction_id);
            preparedStatement.setInt(2, order_id);

            int result_2 = preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(UPDATE_ORDER_UPON_TRANSACTION);
            preparedStatement.setInt(1, transaction_id);
            preparedStatement.setInt(2, order_id);

            int result_4 = preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(UPDATE_BALANCE_ON_RECEIPT);
            preparedStatement.setInt(1, amount);
            preparedStatement.setInt(2, distributor_id);

            int result_3 = preparedStatement.executeUpdate();

            flag = true;


        }catch(Exception e){

            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            //System.out.println(e);
        }
        finally {
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return flag;
        }

    }

}
