package entity;

import java.sql.Date;


public class Publication {
    private Integer publicationId;
    private String publicationTitle;
    private Date publicationDate;
    private PublicationTopic publicationTopic;

    public Publication(Integer publicationId, String publicationTitle, Date publicationDate, PublicationTopic publicationTopic) {
        this.publicationId = publicationId;
        this.publicationTitle = publicationTitle;
        this.publicationDate = publicationDate;
        this.publicationTopic = publicationTopic;
    }

    public Integer getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Integer publicationId) {
        this.publicationId = publicationId;
    }

    public String getPublicationTitle() {
        return publicationTitle;
    }

    public void setPublicationTitle(String publicationTitle) {
        this.publicationTitle = publicationTitle;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public PublicationTopic getPublicationTopic() {
        return publicationTopic;
    }

    public void setPublicationTopic(PublicationTopic publicationTopicId) {
        this.publicationTopic = publicationTopic;
    }

    @Override
    public String toString() {
        return "Id : \t"+publicationId+"\tTitle : \t"+publicationTitle+"\tDate : \t"+
                publicationDate+"\nPublication Topic : "+publicationTopic.getPublicationTopicName();
    }
}
