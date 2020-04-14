package controllers;

import entity.Article;
import entity.WritesArticle;
import entity.WritesBook;
import services.WritesArticleService;

import java.util.ArrayList;
import java.util.List;

public class WritesArticleController {
    private WritesArticleService writesArticleService;

    public WritesArticleController(){
        this.writesArticleService = new WritesArticleService();
    }

    public List<Object> getArticlesByAuthor(String authorName){

        return writesArticleService.getArticlesByAuthor(authorName);
    }

//    public List<WritesArticle> getAuthorsByArticle(Article article) {
//        return writesArticleService.getAuthorsByArticle(article);
//    }

    public boolean deleteWritesArticles(Article article) {
        return writesArticleService.deleteWritesArticleForArticle(article);
    }

    public void addArticlesForAuthor(List<WritesArticle> writesArticles) {
    }
}
