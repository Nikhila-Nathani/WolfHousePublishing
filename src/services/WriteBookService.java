package services;

import constants.Constants;
import entity.*;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WriteBookService {
    private static final String GET_BOOKS_FOR_AUTHOR = "SELECT WB.AUTHOR_ID as A_ID, E.NAME AS E_NAME,E.PAY E_PAY,E.PERIODICITY AS E_PERIODICTY, " +
            "B.PUBLICATION_ID AS PUB_ID, B.ISBN_NO AS ISBN, B.EDITION AS EDITION, " +
            "P.TITLE AS TITLE,P.PUBLICATION_DATE AS PUB_DATE,P.PRICE AS PRICE, PT.ID AS TOPIC_ID,PT.NAME AS TOPIC " +
            "FROM WRITES_BOOK WB, AUTHOR A, EMPLOYEE E, BOOKS B, PUBLICATION P, PUBLICATION_TOPIC PT " +
            "WHERE WB.AUTHOR_ID = A.EMPLOYEE_ID AND A.EMPLOYEE_ID = E.ID AND WB.PUBLICATION_ID = B.PUBLICATION_ID AND " +
            "B.PUBLICATION_ID = P.ID AND P.PUBLICATION_TOPIC = PT.ID AND E.NAME LIKE ?";

    public List<Object> getBooksByAuthor(String authorName){
        List<Object> writesBook = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BOOKS_FOR_AUTHOR);
            preparedStatement.setString(1,authorName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    Employee e = new Employee(resultSet.getInt("A_ID"),resultSet.getString("E_NAME"),
                            resultSet.getFloat("E_PAY"),resultSet.getInt("E_PERIODICTY"));
                    Author a = new Author(e);
                    PublicationTopic pt = new PublicationTopic(resultSet.getInt("TOPIC_ID"),resultSet.getString("TOPIC"));
                    Publication p = new Publication(resultSet.getInt("PUB_ID"),resultSet.getString("TITLE"),
                            resultSet.getDate("PUB_DATE"),resultSet.getInt("PRICE"),pt);
                    Book b = new Book(p,resultSet.getInt("ISBN"),resultSet.getString("EDITION"));
                    writesBook.add(new WritesBook(a,b));
                }
            }
        }catch(Exception e){
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            return writesBook;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return writesBook;
        }
    }
}
