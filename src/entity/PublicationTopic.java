package entity;

public class PublicationTopic {
    private Integer publicationTopicId;
    private String publicationTopicName;

    public Integer getPublicationTopicId() {
        return publicationTopicId;
    }

    public void setPublicationTopicId(Integer publicationTopicId) {
        this.publicationTopicId = publicationTopicId;
    }

    public String getPublicationTopicName() {
        return publicationTopicName;
    }

    public void setPublicationTopicName(String publicationTopicName) {
        this.publicationTopicName = publicationTopicName;
    }

    public PublicationTopic(Integer publicationTopicId, String publicationTopicName) {
        this.publicationTopicId = publicationTopicId;
        this.publicationTopicName = publicationTopicName;
    }

    @Override
    public String toString(){
        return "\t" + this.getPublicationTopicId() + "\t"+this.getPublicationTopicName();
    }
}
