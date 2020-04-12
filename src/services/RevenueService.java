package services;

import constants.Constants;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RevenueService {
    private static final String TOTAL_REVENUE_PER_LOCATION = "SELECT SUM(O.PRICE) AS TOTAL_PRICE,OP.LOCATION AS LOCATION FROM ORDERS O, ORDERS_PLACED OP WHERE O.ID = OP.ORDER_ID GROUP BY(OP.LOCATION)";

    private static final String TOTAL_REVENEUE_PER_DISTRIBUTOR = "SELECT SUM(O.PRICE) AS TOTAL_PRICE, D.NAME AS DNAME, D.ID AS ID FROM ORDERS O, ORDERS_PLACED OP, DISTRIBUTOR D WHERE O.ID = OP.ORDER_ID AND OP.DISTRIBUTOR_ID = D.ID GROUP BY (D.ID)";

    private static final String TOTAL_REVENEUE = "SELECT SUM(T.AMOUNT) FROM TRANSACTIONS T, ORDER_PAYMENT OP WHERE T.ID = OP.TRANSACTION_ID";

    private static final String TOTAL_EXPENSE = "SELECT (A.EMPLOYEE_PAYMENT+B.SHIPPING_COST) FROM" +
            "(SELECT SUM(AMOUNT) AS EMPLOYEE_PAYMENT FROM TRANSACTIONS T WHERE T.ID IN (" +
            " SELECT T.ID FROM TRANSACTIONS T, EMPLOYEE_PAYMENT EP WHERE EP.TRANSACTION_ID = T.ID" +
            " UNION" +
            " SELECT T.ID AS P1 FROM TRANSACTIONS T, CONTRACT C WHERE C.TRANSACTION_ID = T.ID)) AS A," +
            "(SELECT SUM(SHIPPING_COST) AS SHIPPING_COST FROM ORDERS) AS B";

    private static final String TOTAL_REVENUE_PER_CITY = "SELECT CITY AS CITY, SUM(O.PRICE) AS TOTAL_PRICE FROM ORDERS_PLACED OP, ORDERS O, ADDRESS A WHERE O.ID = OP.ORDER_ID AND OP.LOCATION = A.LOCATION   GROUP BY (A.CITY)";

    private static final String PRICE_PER_DISTRIBUTOR_PER_PUBLICATION =  "SELECT D.NAME, P.TITLE, SUM(OC.NO_OF_COPIES) AS TOTAL_COPIES, P.PRICE*SUM(OC.NO_OF_COPIES) AS VALUE FROM PUBLICATION P,ORDERS_PLACED OP, ORDER_CONTAINS OC, DISTRIBUTOR D WHERE OP.ORDER_ID = OC.ORDER_ID AND OP.DISTRIBUTOR_ID = D.ID AND OC.PUBLICATION_ID = P.ID GROUP BY (D.ID), (OC.PUBLICATION_ID)";

    private static final String TOTAL_PAYMENTS_PER_WORK_TYPE = "SELECT A.BOOK_SUM, B.ARTICLE_SUM, C.EDIT_SUM FROM\n"+
            "(SELECT SUM(AMOUNT) AS BOOK_SUM FROM EMPLOYEE_PAYMENT EP, TRANSACTIONS T WHERE EP.EMPLOYEE_ID IN(\n"+
            "    SELECT WB.AUTHOR_ID FROM BOOKS B, WRITES_BOOK WB) AND T.ID = EP.TRANSACTION_ID) AS A,\n"+
            "(SELECT SUM(AMOUNT)  AS ARTICLE_SUM FROM EMPLOYEE_PAYMENT EP, TRANSACTIONS T WHERE EP.EMPLOYEE_ID IN(\n"+
            "    SELECT WA.AUTHOR_ID FROM ARTICLE A, WRITES_ARTICLE WA) AND T.ID = EP.TRANSACTION_ID) AS B,\n"+
            "(SELECT SUM(AMOUNT) AS EDIT_SUM FROM EMPLOYEE_PAYMENT EP, TRANSACTIONS T WHERE EP.EMPLOYEE_ID IN(\n"+
            "    SELECT E.EDITOR_ID  FROM EDITS E) AND T.ID = EP.TRANSACTION_ID) AS C";

    public Map<String,Integer> getTotalRevenuePerLocation(){
            Map<String,Integer> answer = new HashMap<>();
            Connection connection = null;
            try{
                connection = DatabaseUtility.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(TOTAL_REVENUE_PER_LOCATION);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(resultSet!=null){
                    while(resultSet.next()){
                        if(resultSet.getString("LOCATION")!=null){
                            answer.put(resultSet.getString("LOCATION"),resultSet.getInt("TOTAL_PRICE"));
                        }
                    }
                }
            } catch(Exception e){
                if(connection!=null){
                    System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
                }else{
                    System.out.println(Constants.CONNECTION_ERROR.getMessage());
                }
                return answer;
            } finally{
                try{
                    DatabaseUtility.closeconnection();
                }catch(Exception e){
                    System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
                }
                return answer;
            }
        }

    public Map<Integer,Map<String, Integer>> getTotalRevenuePerDistributor() {
        Map<Integer,Map<String,Integer>> answer = new HashMap<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(TOTAL_REVENEUE_PER_DISTRIBUTOR);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                        Map<String,Integer> map = new HashMap<>();
                        map.put(resultSet.getString("DNAME"),resultSet.getInt("TOTAL_PRICE"));
                        answer.put(resultSet.getInt("ID"),map);
                }
            }
        } catch(Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return answer;
        } finally{
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return answer;
        }
    }

    public int getTotalRevenue() {
        int answer = -1;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(TOTAL_REVENEUE);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                answer = resultSet.getInt(1);
            }
        } catch(Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return answer;
        } finally{
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return answer;
        }
    }


    public int getTotalExpense() {
        int answer = -1;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(TOTAL_EXPENSE);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                answer = resultSet.getInt(1);
            }
        } catch(Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return answer;
        } finally{
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return answer;
        }
    }

    public Map<String,Integer> getTotalRevenuePerCity(){
        Map<String,Integer> answer = new HashMap<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(TOTAL_REVENUE_PER_CITY);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    if(resultSet.getString("CITY")!=null){
                        answer.put(resultSet.getString("CITY"),resultSet.getInt("TOTAL_PRICE"));
                    }
                }
            }
        } catch(Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return answer;
        } finally{
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return answer;
        }
    }


    public Map<String, List<List<Object>>> getPerDistributorPerPublicationPrice(){
        Map<String,List<List<Object>>> answer = new HashMap();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(PRICE_PER_DISTRIBUTOR_PER_PUBLICATION);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    String dname = resultSet.getString("NAME");
                    String title = resultSet.getString("TITLE");
                    int totalCopies = resultSet.getInt("TOTAL_COPIES");
                    int value = resultSet.getInt("VALUE");
                    List<Object> list = new ArrayList<>();
                    list.add(title);
                    list.add(totalCopies);
                    list.add(value);
                    List<List<Object>> current;
                    if(answer.containsKey(dname)){
                         current = answer.get(dname);
                    }else{
                        current = new ArrayList<>();
                    }
                    current.add(list);
                    answer.put(dname,current);
                }
            }
        } catch(Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return answer;
        } finally{
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return answer;
        }
    }

    public List<Integer> getPaymentsPerWorkType(){
        List<Integer> answer = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(TOTAL_PAYMENTS_PER_WORK_TYPE);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    answer.add(resultSet.getInt("BOOK_SUM"));
                    answer.add(resultSet.getInt("ARTICLE_SUM"));
                    answer.add(resultSet.getInt("EDIT_SUM"));
                }
            }
        } catch(Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return answer;
        } finally{
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return answer;
        }
    }
}
