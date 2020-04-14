package entity;

import java.sql.Date;

public class Article {
    private Integer articleId;
    private String title;
    private Date dateOfCreation;
    private String text;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }


    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Article(Integer articleId, String title, Date dateOfCreation, String text) {
        this.articleId = articleId;
        this.title = title;
        this.dateOfCreation = dateOfCreation;
        this.text = text;
    }


    public Article(String title, Date dateOfCreation, String text) {
        this.title = title;
        this.dateOfCreation = dateOfCreation;
        this.text = text;
    }

    @Override
    public String toString() {
        return "\t"+articleId+"\t"+title+"\t"+dateOfCreation;
    }
}
