package controllers;

import constants.Constants;
import entity.Article;
import entity.Edits;
import entity.HasArticle;
import entity.PeriodicPublication;
import services.HasArticleService;
import utility.DatabaseUtility;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HasArticleController {
    private HasArticleService hasArticleService;

    public HasArticleController(){
        hasArticleService = new HasArticleService();
    }

    public HasArticle addArticleToPeriodicPublication(List<HasArticle> hasArticles){
        HasArticle result = null;
        try {
            Connection connection = DatabaseUtility.getConnection();
            DatabaseUtility.beginTransaction();
            for(HasArticle hs : hasArticles){
                if(!hasArticleService.addArticleToPeriodicPublication(hs,connection)){
                    connection.rollback();
                    result = hs;
                    return hs;
                }
            }
            connection.commit();
            DatabaseUtility.endTransaction();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
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
        return hasArticleService.getArticlesForPeriodicPublication(periodicPublication);
    }

    public HasArticle deleteArticlesForPeriodicPublication(List<HasArticle> hasArticles) {
        for(HasArticle hasArticle:hasArticles){
            if(!hasArticleService.deleteArticlesForPeriodicPublication(hasArticle)){
                return hasArticle;
            }
        }
        return null;
    }

//    public List<HasArticle> getPeriodicPublicationForArticles(Article article) {
//        return hasArticleService.getPeriodicPublicationForArticles(article);
//    }

    public boolean deletePublicationsForArticles(Article currentArticle) {
        return hasArticleService.deletePerioidicPublicationForArticles(currentArticle);
    }
}
