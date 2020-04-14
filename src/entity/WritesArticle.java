package entity;

public class WritesArticle {

    Integer authorId;
    Integer articleId;

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public WritesArticle(Integer authorId, Integer articleId) {
        this.authorId = authorId;
        this.articleId = articleId;
    }
}
