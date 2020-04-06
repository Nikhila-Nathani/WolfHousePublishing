package entity;

import java.sql.Date;


public class Publication {
    private Integer publicationId;
    private String publicationTitle;
    private Date publicationDate;
    private PublicationTopic publicationTopic;
    private Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Publication(Integer publicationId, String publicationTitle, Date publicationDate,Integer price, PublicationTopic publicationTopic) {
        this.publicationId = publicationId;
        this.publicationTitle = publicationTitle;
        this.publicationDate = publicationDate;
        this.publicationTopic = publicationTopic;
        this.price = price;
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

        return "\t"+publicationId+"\t"+publicationTitle+"\t"+publicationDate+"\t"+price+"\t"+publicationTopic.getPublicationTopicName();

        /*return "Id : \t"+publicationId+"\tTitle : \t"+publicationTitle+"\tDate : \t"+
                publicationDate+"\nPublication Topic : "+publicationTopic.getPublicationTopicName();*/
    }
}
