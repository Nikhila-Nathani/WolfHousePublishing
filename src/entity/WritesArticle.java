package entity;

public class WritesArticle {

    private Author author;
    private String articleTitle;

    public WritesArticle(Author authorId, String articleTitle) {
        this.author = author;
        this.articleTitle = articleTitle;
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    @Override
    public String toString() {
        return "\t"+articleTitle+"\t"+author.getEmployee().getEmployeeName();
    }
}
