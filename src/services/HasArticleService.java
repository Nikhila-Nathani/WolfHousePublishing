package services;

import constants.Constants;
import entity.Article;
import entity.HasArticle;
import entity.PeriodicPublication;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HasArticleService {

    private static final String ADD_ARTICLE_TO_PERIODIC_PUBLICATION = "INSERT INTO HAS_ARTICLE(PERIODIC_PUBLICATION_ID, ARTICLE_TITLE) VALUES(?,?)";
    private static final String GET_ARTICLES_FOR_PERIODIC_PUBLICATION = "SELECT A.TITLE AS TITLE, A.DATE_OF_CREATION DATE_OF_CREATION FROM HAS_ARTICLE H_ARTICLE, ARTICLE A WHERE A.TITLE = H_ARTICLE.ARTICLE_TITLE AND H_ARTICLE.PERIODIC_PUBLICATION_ID =?";
    private static final String DELETE_ARTICLE_FOR_PERIODIC_PUBLICATION = "DELETE FROM HAS_ARTICLE WHERE PERIODIC_PUBLICATION_ID = ? AND ARTICLE_TITLE = ?";

    public boolean addArticleToPeriodicPublication(HasArticle hasArticle){
        boolean result = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ARTICLE_TO_PERIODIC_PUBLICATION);
            preparedStatement.setInt(1,hasArticle.getPeriodicPublication().getPublication().getPublicationId());
            preparedStatement.setString(2,hasArticle.getArticleTitle());
            int flag = preparedStatement.executeUpdate();
            result = flag == 1? true:false;
        }catch(Exception e){
            if(connection!=null){
                System.out.println(Constants.CONSTRAINT_VIOLATED.getMessage());
            }else {
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return result;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return result;
        }

    }


    public List<Object> getArticlesForPeriodicPublication(PeriodicPublication periodicPublication){
        List<Object> articles = new ArrayList<>();
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ARTICLES_FOR_PERIODIC_PUBLICATION);
            preparedStatement.setInt(1,periodicPublication.getPublication().getPublicationId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet!=null){
                while(resultSet.next()){
                    articles.add(new Article(resultSet.getString("TITLE"),resultSet.getDate("DATE_OF_CREATION")));
                }
            }
        }catch (Exception e){
            if(connection !=null){
                System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
            return articles;
        }finally {
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return  articles;
        }
    }

    public boolean deleteArticlesForPeriodicPublication(HasArticle hasArticle) {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ARTICLE_FOR_PERIODIC_PUBLICATION);
            preparedStatement.setInt(1,hasArticle.getPeriodicPublication().getPublication().getPublicationId());
            preparedStatement.setString(2,hasArticle.getArticleTitle());
            int result = preparedStatement.executeUpdate();
            flag = result == 1? true: false;
        }catch(Exception e){
            if(connection!=null){
                System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
            }else{
                System.out.println(Constants.CONNECTION_ERROR.getMessage());
            }
        }finally{
            try{
                DatabaseUtility.closeconnection();
            }catch (Exception e){
                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
            }
            return flag;
        }

    }
}
