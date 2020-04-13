package services;

import constants.Constants;
import entity.*;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class WritesArticleService {

    private static final String GET_ARTICLES_FOR_AUTHOR = "SELECT WA.ARTICLE_TITLE, WA.AUTHOR_ID as A_ID, E.NAME AS E_NAME,E.PAY E_PAY,E.PERIODICITY AS E_PERIODICTY FROM WRITES_ARTICLE WA, AUTHOR A, EMPLOYEE E WHERE WA.AUTHOR_ID = A.EMPLOYEE_ID AND A.EMPLOYEE_ID = E.ID AND E.NAME LIKE ?";
    private static final String GET_AUTHOR_FOR_ARTICLES = "SELECT WA.ARTICLE_TITLE, WA.AUTHOR_ID as A_ID, E.NAME AS E_NAME,E.PAY E_PAY,E.PERIODICITY AS E_PERIODICTY FROM WRITES_ARTICLE WA, AUTHOR A, EMPLOYEE E WHERE WA.AUTHOR_ID = A.EMPLOYEE_ID AND A.EMPLOYEE_ID = E.ID AND ARTICLE_TITLE LIKE ?" ;
    private static final String DELETE_WRITES_ARTICLE_FOR_ARTICLE = "DELETE FROM WRITES_ARTICLE WHERE ARTICLE_TITLE = ?";

    public List<Object> getArticlesByAuthor(String authorName){
        List<Object> writesArticles = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ARTICLES_FOR_AUTHOR);
            preparedStatement.setString(1,authorName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    Employee e = new Employee(resultSet.getInt("A_ID"),resultSet.getString("E_NAME"),
                            resultSet.getFloat("E_PAY"),resultSet.getInt("E_PERIODICTY"));
                    Author a = new Author(e);
                    writesArticles.add(new WritesArticle(a,resultSet.getString("ARTICLE_TITLE")));
                }
            }
        }catch(Exception e){
            if(connection == null){
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }

            return writesArticles;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return writesArticles;
        }
    }

    public List<WritesArticle> getAuthorsByArticle(Article article) {
        List<WritesArticle> writesArticles = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_AUTHOR_FOR_ARTICLES);
            preparedStatement.setString(1,article.getTitle());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    Employee e = new Employee(resultSet.getInt("A_ID"),resultSet.getString("E_NAME"),
                            resultSet.getFloat("E_PAY"),resultSet.getInt("E_PERIODICTY"));
                    Author a = new Author(e);
                    writesArticles.add(new WritesArticle(a,resultSet.getString("ARTICLE_TITLE")));
                }
            }
        }catch(Exception e){
            if(connection == null){
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }

            return writesArticles;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return writesArticles;
        }
    }

    public boolean deleteWritesArticleForArticle(Article article) {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_WRITES_ARTICLE_FOR_ARTICLE);
            preparedStatement.setString(1,article.getTitle());
            int result = preparedStatement.executeUpdate();
            if(result>0){
                flag = true;
            }
        }catch(Exception e){
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
