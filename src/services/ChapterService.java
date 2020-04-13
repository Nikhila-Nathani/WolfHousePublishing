package services;

import constants.Constants;
import entity.Book;
import entity.Chapter;
import entity.Publication;
import entity.PublicationTopic;
import utility.DatabaseUtility;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChapterService {

    private static final String ADD_NEW_CHAPTER = "INSERT INTO CHAPTER (BOOK_ID, CHAPTER_NO, TEXT) VALUES (?,?,?)";
    private static final String GET_CHAPTERS_FOR_BOOK = "SELECT C.CHAPTER_NO AS CHAPTER_NUMBER, C.TEXT AS CHAPTER_TEXT, " +
            "B.PUBLICATION_ID AS PUB_ID, B.ISBN_NO AS ISBN, B.EDITION AS EDITION, " +
            "P.TITLE AS TITLE, P.PUBLICATION_DATE AS PUBLICATION_DATE, P.PRICE AS PRICE," +
            " P.PUBLICATION_TOPIC AS TOPIC_ID, PT.NAME AS TOPIC " +
            "FROM CHAPTER C, BOOKS B, PUBLICATION P, PUBLICATION_TOPIC PT " +
            "WHERE C.BOOK_ID = B.PUBLICATION_ID AND B.PUBLICATION_ID = P.ID AND P.PUBLICATION_TOPIC = PT.ID AND C.BOOK_ID = ?";

    private static final String DELETE_CHAPTER = "DELETE FROM CHAPTER WHERE BOOK_ID = ? AND CHAPTER_NO = ?";


    public boolean addNewChapter(Chapter chapter){
        boolean flag = false;
        Connection connection = null;
        try{
            connection =DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_CHAPTER);
            preparedStatement.setInt(1,chapter.getBook().getPublication().getPublicationId());
            preparedStatement.setInt(2,chapter.getChapterNumber());
            preparedStatement.setString(3,chapter.getText());
            int result = preparedStatement.executeUpdate();
            flag = result == 1?true:false;
        }catch (Exception e) {
            if(connection!=null) {
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

    public List<Object> getChaptersForABook(Book book){
        List<Object> chapters = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_CHAPTERS_FOR_BOOK);
            preparedStatement.setInt(1,book.getPublication().getPublicationId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    chapters.add(new Chapter(new Book(new Publication(resultSet.getInt("PUB_ID"),resultSet.getString("TITLE"),
                            resultSet.getDate("PUBLICATION_DATE"),resultSet.getInt("PRICE"),
                            new PublicationTopic(resultSet.getInt("TOPIC_ID"),resultSet.getString("TOPIC"))),
                            resultSet.getInt("ISBN"),resultSet.getString("EDITION")),
                            resultSet.getInt("CHAPTER_NUMBER"),resultSet.getString("CHAPTER_TEXT")));
                }
            }
        }catch(Exception e){
            System.out.println(Constants.CONNECTION_ERROR.getMessage());
            return chapters;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return chapters;
        }
    }

    public boolean deleteChapter(Chapter chapter) {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CHAPTER);
            preparedStatement.setInt(1,chapter.getBook().getPublication().getPublicationId());
            preparedStatement.setInt(2,chapter.getChapterNumber());
            int result = preparedStatement.executeUpdate();
            flag = result == 1? true:false;
        }catch(Exception e){
            if(connection!=null) {
                System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
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
