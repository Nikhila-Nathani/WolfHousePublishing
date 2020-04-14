package services;

import constants.Constants;
import entity.*;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HasArticleService {

    private static final String ADD_ARTICLE_TO_PERIODIC_PUBLICATION = "INSERT INTO HAS_ARTICLE(PUBLICATION_ID, ARTICLE_ID) VALUES(?,?)";
    private static final String GET_ARTICLES_FOR_PERIODIC_PUBLICATION = "SELECT A.ID AS A_ID, A.TITLE AS A_TITLE, A.DATE_OF_CREATION AS DOC, A.TEXT AS TEXT FROM ARTICLE A, HAS_ARTICLE H_ARTICLE WHERE A.ID = H_ARTICLE.ARTICLE_ID AND H_ARTICLE.PUBLICATION_ID =?";
    private static final String DELETE_ARTICLE_FOR_PERIODIC_PUBLICATION = "DELETE FROM HAS_ARTICLE WHERE PUBLICATION_ID = ? AND ARTICLE_ID = ?";
//    private static final String GET_PERIODIC_PUBLICATION_FOR_ARTICLE = "SELECT P.ID AS P_ID, P.TITLE AS TITLE, P.PUBLICATION_DATE AS P_DATE, P.PRICE AS PRICE, P.PUBLICATION_TOPIC AS TOPIC_ID, PT.NAME AS TOPIC, PER.ID AS PERID, PER.NAME AS PERNAME, H_ARTICLE.ARTICLE_TITLE AS ARTICLE_TITLE  FROM PERIODICITY PER, HAS_ARTICLE H_ARTICLE, ARTICLE A, PERIODIC_PUBLICATION PP, PUBLICATION P, PUBLICATION_TOPIC PT WHERE A.TITLE = H_ARTICLE.ARTICLE_TITLE AND H_ARTICLE.PERIODIC_PUBLICATION_ID = PP.PUBLICATION_ID AND PP.PUBLICATION_ID = P.ID AND P.PUBLICATION_TOPIC = PT.ID AND H_ARTICLE.ARTICLE_TITLE = ?";
    private static final String DELETE_PERIODIC_PUBLICATION_FOR_ARTICLE = "DELETE FROM HAS_ARTICLE WHERE ARTICLE_ID = ?";;

    public boolean addArticleToPeriodicPublication(HasArticle hasArticle,Connection connection){
        boolean result = false;
        //Connection connection = null;
        try{
          //  connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ARTICLE_TO_PERIODIC_PUBLICATION);
            System.out.println(hasArticle.getPeriodicPublicationIdn());
            System.out.println(hasArticle.getArticleId());
            preparedStatement.setInt(1,hasArticle.getPeriodicPublicationIdn());
            preparedStatement.setInt(2,hasArticle.getArticleId());
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
                    articles.add(
                            new Article(
                                    resultSet.getInt("A_ID"),resultSet.getString("A_TITLE"),
                                    resultSet.getDate("DOC"),resultSet.getString("TEXT")
                            )
                    );
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
            preparedStatement.setInt(2,hasArticle.getArticleId());
            preparedStatement.setInt(1,hasArticle.getPeriodicPublicationIdn());
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


    public boolean deletePerioidicPublicationForArticles(Article article) {
        boolean flag = false;
        Connection connection = null;
        try{
            connection = DatabaseUtility.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PERIODIC_PUBLICATION_FOR_ARTICLE);
            preparedStatement.setInt(1,article.getArticleId());
            int result = preparedStatement.executeUpdate();
            if(result>=0){
                flag = true;
            }
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

//    public List<HasArticle> getPeriodicPublicationForArticles(Article article) {
//        List<HasArticle> hasArticles = new ArrayList<>();
//        Connection connection = null;
//        try{
//            connection = DatabaseUtility.getConnection();
//
//            PreparedStatement preparedStatement = connection.prepareStatement(GET_PERIODIC_PUBLICATION_FOR_ARTICLE);
//            preparedStatement.setString(1,article.getTitle());
//            ResultSet resultSet = preparedStatement.executeQuery();
//            if(resultSet!=null){
//                while(resultSet.next()){
//
//                    PublicationTopic pt =new PublicationTopic(resultSet.getInt("TOPIC_ID"),
//                            resultSet.getString("TOPIC"));
//                    Publication p = new Publication(resultSet.getInt("P_ID"),
//                            resultSet.getString("TITLE"), resultSet.getDate("P_DATE"),
//                            resultSet.getInt("PRICE"),pt);
//                    Periodicity per = new Periodicity(resultSet.getInt("PERID"),
//                            resultSet.getString("PERNAME"));
//                    PeriodicPublication pp = new PeriodicPublication(p,per);
//                    HasArticle ha = new HasArticle(pp,resultSet.getString("ARTICLE_TITLE"));
//                    hasArticles.add(ha);
//                }
//            }
//        }catch (Exception e){
//            if(connection !=null){
//                System.out.println(Constants.RECORD_NOT_FOUND.getMessage());
//            }else{
//                System.out.println(Constants.CONNECTION_ERROR.getMessage());
//            }
//            return hasArticles;
//        }finally {
//            try{
//                DatabaseUtility.closeconnection();
//            }catch (Exception e){
//                System.out.println(Constants.CONNECTION_CLOSE_ERROR.getMessage());
//            }
//            return  hasArticles;
//        }
//    }
}
