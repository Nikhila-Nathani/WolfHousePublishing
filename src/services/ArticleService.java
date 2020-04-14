package services;

import constants.Constants;
import entity.Article;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticleService {

    private static final String GET_ALL_ARTICLES = "SELECT A.ID AS A_ID, A.TITLE AS A_TITLE, A.DATE_OF_CREATION AS DOC, A.TEXT AS TEXT  FROM ARTICLE A";
    private static final String GET_ARTICLES_BY_TOPIC = "SELECT A.ID AS A_ID, A.TITLE AS A_TITLE, A.DATE_OF_CREATION AS DOC, A.TEXT AS TEXT " +
            "FROM ARTICLE A, HAS_ARTICLE H_ARTICLE, PERIODIC_PUBLICATION PP, PUBLICATION P, PUBLICATION_TOPIC PT " +
            "WHERE H_ARTICLE.ARTICLE_ID = A.ID AND H_ARTICLE.PUBLICATION_ID = PP.PUBLICATION_ID AND PP.PUBLICATION_ID = P.ID AND P.PUBLICATION_TOPIC = PT.ID AND PT.NAME = ?";
    private static final String GET_ARTICLES_BY_DATE = "SELECT A.ID AS ARTICLE_ID, A.TITLE AS A_TITLE, A.DATE_OF_CREATION AS DOC, A.TEXT AS TEXT FROM ARTICLE AS A WHERE  DATE_OF_CREATION = ?";

    private static final String CREATE_ARTICLE = "INSERT INTO ARTICLE (TITLE,DATE_OF_CREATION,TEXT) VALUES(?,?,?)";
    private static final String DELETE_ARTICLE = "DELETE  FROM ARTICLE WHERE ID = ?";
    private static final String UPDATE_ARTICLE = "UPDATE ARTICLE SET TITLE = ? , DATE_OF_CREATION = ?, TEXT = ? WHERE ID = ?";

    public List<Object> getAllArticles(){
        List<Object> articles = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ARTICLES);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    Article a = new Article(
                            resultSet.getInt("A_ID"),resultSet.getString("A_TITLE"),
                            resultSet.getDate("DOC"),resultSet.getString("TEXT")
                    );
                    articles.add(a);
                }
            }
        }catch(Exception e){
            if(connection == null){
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return articles;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch(Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return articles;
        }
    }

    public List<Object> getArticlesByTopic(String topic){
        List<Object> articles = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ARTICLES_BY_TOPIC);
            preparedStatement.setString(1,topic);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    Article a = new Article(
                            resultSet.getInt("A_ID"),resultSet.getString("A_TITLE"),
                            resultSet.getDate("DOC"),resultSet.getString("TEXT")
                    );
                    articles.add(a);
                }
            }
        }catch(Exception e){
            if(connection==null){
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return articles;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return articles;
        }
    }

    public List<Object> getArticlesByDate(String date){
        List<Object> articles = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ARTICLES_BY_DATE);
            preparedStatement.setDate(1, Date.valueOf(date));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    articles.add(new Article(
                            resultSet.getInt("ARTICLE_ID"),resultSet.getString("TITLE"),
                            resultSet.getDate("DOC"),resultSet.getString("TEXT")
                    ));
                }
            }
        }catch(Exception e){
            if(connection== null){
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }

            return articles;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return articles;
        }
    }

    public boolean createArticle(Article article){
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_ARTICLE);
            preparedStatement.setString(1,article.getTitle());
            preparedStatement.setDate(2,article.getDateOfCreation());
            preparedStatement.setString(3,article.getText());
            int result = preparedStatement.executeUpdate();
            flag = result ==1?true:false;

        }catch(Exception e){
            if(connection!=null){
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

    public boolean deleteArticle(Article article) {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ARTICLE);
            preparedStatement.setInt(1,article.getArticleId());
            int result = preparedStatement.executeUpdate();
            flag = result ==1?true:false;
        }catch(Exception e){
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

    public boolean updateArticle(Article currentArticle) {
        Connection connection = null;
        boolean flag = false;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ARTICLE);
            preparedStatement.setDate(2,currentArticle.getDateOfCreation());
            preparedStatement.setString(1,currentArticle.getTitle());
            preparedStatement.setString(3,currentArticle.getText());
            preparedStatement.setInt(4, currentArticle.getArticleId());
            int result = preparedStatement.executeUpdate();
            flag = result == 1?true:false;
        }catch(Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            }else{
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
