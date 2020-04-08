package entity;

import java.sql.Date;

public class Article {
    private String title;
    private Date dateOfCreation;

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

    public Article(String title, Date dateOfCreation) {
        this.title = title;
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public String toString() {
        return "\t"+title+"\t"+dateOfCreation;
    }
}
