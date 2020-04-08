package entity;

public class WritesBook {

    private Author author;
    private Book book;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }


    public WritesBook(Author author, Book book) {
        this.author = author;
        this.book = book;
    }

    @Override
    public String toString() {
        return "\t"+book.getPublication().getPublicationId()+"\t"+book.getPublication().getPublicationTitle()+
                "\t"+author.getEmployee().getEmployeeName();
    }
}
