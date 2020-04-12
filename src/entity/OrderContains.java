package entity;

public class OrderContains {

    private Integer orderId;
    private Publication publication;
    private Integer numberOfCopies;

    public OrderContains(Integer orderId, Publication publication, Integer numberOfCopies) {
        this.orderId = orderId;
        this.publication = publication;
        this.numberOfCopies = numberOfCopies;
    }

    public OrderContains(Publication publication, Integer numberOfCopies) {
        this.publication = publication;
        this.numberOfCopies = numberOfCopies;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublicationId(Publication publication) {
        this.publication = publication;
    }

    public Integer getNumberOfCopies() {
        return numberOfCopies;
    }

    @Override
    public String toString() {
        return "Order Id=\t" + orderId +
                "\tPublication Id=\t" + publication.getPublicationId() +
                "\t Publication Title=\t"+ publication.getPublicationTitle()+
                "\tnumberOfCopies=" + numberOfCopies;
    }

    public void setNumberOfCopies(Integer numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }
}
