package entity;

public class OrderPlaced {

    private Integer distributorId;
    private Integer orderId;
    private String location;

    @Override
    public String toString() {
        return "Distributor Id=\t" + distributorId +
                "\tOrder Id=\t" + orderId +
                "\tLocation=\t" + location;
    }

    public OrderPlaced(Integer distributorId, Integer orderId, String location) {
        this.distributorId = distributorId;
        this.orderId = orderId;
        this.location = location;
    }

    public Integer getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Integer distributorId) {
        this.distributorId = distributorId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
