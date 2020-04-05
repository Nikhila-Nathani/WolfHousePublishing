package entity;

public class WritesArticle {

    private Integer authorId;
    private String articleTitle;

    public WritesArticle(Integer authorId, String articleTitle) {
        this.authorId = authorId;
        this.articleTitle = articleTitle;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    @Override
    public String toString() {
        return "Author Id:\t" + authorId +
                "\tArticle Title:\t" + articleTitle;
    }
}
