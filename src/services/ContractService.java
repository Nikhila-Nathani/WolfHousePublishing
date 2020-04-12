package services;

import constants.Constants;
import entity.Contract;
import entity.Employee;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ContractService {

    private static final String GET_CONTRACTS_FOR_PAYMENTS = "SELECT C.ID AS C_ID, C.START_DATE AS SD, C.END_DATE AS ED, C.PAY AS C_PAY, E.ID AS EMPLOYEE_ID, E.NAME AS NAME, E.PAY AS E_PAY, E.PERIODICITY AS PERIODICITY , TRANSACTION_ID FROM CONTRACT C, EMPLOYEE E WHERE END_DATE >=? AND END_DATE<= ? AND TRANSACTION_ID IS  NULL AND E.ID = C.EMPLOYEE_ID";
    private static final String UPDATE_TRANSACTION_ID = "UPDATE CONTRACT SET TRANSACTION_ID = ? WHERE ID = ?";

    public List<Object> getContractsForDate(Date date){
        List<Object> contracts = new ArrayList<>();
        Connection connection = null;
        try{

            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CONTRACTS_FOR_PAYMENTS);

//            Calendar calendar = Calendar.getInstance();
            String d = date.toString();
            String d1[] = d.split("-");
            int currentMonth = Integer.parseInt(d1[1]);
            int currentYear = Integer.parseInt(d1[0]);
//            calendar.set(currentYear,currentMonth,1);
//            int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//            System.out.println(currentMonth);
//            System.out.println(currentYear);
//            System.out.println(Integer.toString(days));
//            Date startDate = Date.valueOf(Integer.toString(currentYear) +"-"+Integer.toString(currentMonth)+"-01");
//            System.out.println(currentMonth);
//            System.out.println(currentYear);
//            System.out.println(Integer.toString(days));
//            String eD = currentYear +"-"+(currentMonth)+"-"+days;
//            Date endDate = Date.valueOf(eD);
//
//            System.out.println(startDate.toString());
//            System.out.println(endDate.toString());
//           System.exit(0);


            LocalDate sD = LocalDate.of(currentYear,currentMonth,01);
            LocalDate eD = LocalDate.of(currentYear,currentMonth,30);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            Date startDate = Date.valueOf("2020-04-01");
            Date endDate = Date.valueOf("2020-04-30");
            preparedStatement.setDate(1,Date.valueOf(dtf.format(sD)));
            preparedStatement.setDate(2,Date.valueOf(dtf.format(eD)));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    contracts.add(new Contract(resultSet.getInt("C_ID"),resultSet.getDate("SD"),
                            resultSet.getDate("ED"),resultSet.getInt("C_PAY"),
                            new Employee(resultSet.getInt("EMPLOYEE_ID"),resultSet.getString("NAME"),
                                    resultSet.getFloat("E_PAY"),resultSet.getInt("PERIODICITY")
                            )
                    ));
                }
            }
        } catch(Exception e){
            if(connection==null){
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return contracts;
        } finally {
            try {
                DatabaseUtility.closeconnection();
            } catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return contracts;
        }
    }

    public boolean updateTransactionIdForContract(Contract contract) {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TRANSACTION_ID);
            preparedStatement.setInt(1,contract.getTransactionId());
            preparedStatement.setInt(2,contract.getContractId());
            int result = preparedStatement.executeUpdate();
            flag = result ==1? true:false;
        }catch(Exception e){
            if(connection == null){
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return flag;
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
