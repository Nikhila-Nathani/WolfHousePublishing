package entity;

public class HasArticle {
    PeriodicPublication periodicPublication;
    String articleTitle;

    public HasArticle(PeriodicPublication periodicPublication, String articleTitle) {
        this.periodicPublication = periodicPublication;
        this.articleTitle = articleTitle;
    }

    @Override
    public String toString() {
        return "Publication Name : \t"+periodicPublication.getPublication().getPublicationTitle()+"\tArticle : \t"+articleTitle;
    }

    public PeriodicPublication getPeriodicPublication() {
        return periodicPublication;
    }

    public void setPeriodicPublication(PeriodicPublication periodicPublication) {
        this.periodicPublication = periodicPublication;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }
}
