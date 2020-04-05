package entity;

public class Book {
    private Publication publication;
    private Integer isbnNumber;
    private String edition;

    public Book(Publication publication, Integer isbnNumber, String edition) {
        this.publication = publication;
        this.isbnNumber = isbnNumber;
        this.edition = edition;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Integer getIsbnNumber() {
        return isbnNumber;
    }

    public void setIsbnNumber(Integer isbnNumber) {
        this.isbnNumber = isbnNumber;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    @Override
    public String toString() {
        return "Id : \t"+ publication +"\tISBN : \t"+isbnNumber+"\tEdition : \t"+
                edition;
    }
}
