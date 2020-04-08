package entity;

public class Chapter {

    private Book book;
    private Integer chapterNumber;
    private String text;

    public Chapter(Book book, Integer chapterNumber, String text) {
        this.book = book;
        this.chapterNumber = chapterNumber;
        this.text = text;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getChapterNumber() {
        return chapterNumber;
    }

    public void setChapterNumber(Integer chapterNumber) {
        this.chapterNumber = chapterNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "\t" + book.getPublication().getPublicationId() + "\t"+ book.getPublication().getPublicationTitle()+
                "\t" + chapterNumber;
    }

    public String toString(int i){
        return "\t" + book.getPublication().getPublicationId() + "\t"+ book.getPublication().getPublicationTitle()+
                "\t" + chapterNumber +
                "\t" + text;
    }
}
