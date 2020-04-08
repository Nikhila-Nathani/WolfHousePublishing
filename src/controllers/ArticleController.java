package controllers;

import entity.Article;
import services.ArticleService;

import java.util.List;

public class ArticleController {
    private ArticleService articleService;

    public ArticleController(){
        articleService = new ArticleService();
    }

    public List<Object> getAllArticles(){
        return articleService.getAllArticles();
    }

    public List<Object> getArticlesByTopic(String topic) { return articleService.getArticlesByTopic(topic); }

    public List<Object> getArticlesByDate(String date) { return articleService.getArticlesByDate(date); }

    public boolean createArticle(Article article){ return articleService.createArticle(article); }

    public boolean deleteArticle(Article article) {
        return articleService.deleteArticle(article);
    }
}
