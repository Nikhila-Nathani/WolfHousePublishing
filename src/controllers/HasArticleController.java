package controllers;

import entity.HasArticle;
import entity.PeriodicPublication;
import services.HasArticleService;

import java.util.List;

public class HasArticleController {
    private HasArticleService hasArticleService;

    public HasArticleController(){
        hasArticleService = new HasArticleService();
    }

    public HasArticle addArticleToPeriodicPublication(List<HasArticle> hasArticles){
        for(HasArticle hs : hasArticles){
            if(!hasArticleService.addArticleToPeriodicPublication(hs)){
                return hs;
            }
        }
        return null;
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
}
