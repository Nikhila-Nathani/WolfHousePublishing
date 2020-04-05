package entity;

public class WritesBook {

    private Integer authorId;
    private Integer publicationId;

    public WritesBook(Integer authorId, Integer publicationId) {
        this.authorId = authorId;
        this.publicationId = publicationId;
    }

    @Override
    public String toString() {
        return "Author Id : " + authorId +
                "\t Publication Id :\t" + publicationId;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Integer publicationId) {
        this.publicationId = publicationId;
    }
}
