package entity;

import java.sql.Date;

public class Order {

    private Integer orderId;
    private Float price;
    private Date orderDate;
    private Float shippingCost;
    private Date deliveryDate;
    private Integer transactionId;

    @Override
    public String toString() {
        String result="";

        result = "\t"+orderId+"\t"+price+"\t"+orderDate+"\t"+shippingCost+"\t"+deliveryDate;
        if (transactionId==null){
            result+= "\tTRANSACTION ID NOT YET GENERATED";
        } else{
            result+="\t"+transactionId;
        }

        return result;
    }

    public Order(Integer orderId, Float price, Date orderDate, Float shippingCost, Date deliveryDate, Integer transactionId) {
        this.orderId = orderId;
        this.price = price;
        this.orderDate = orderDate;
        this.shippingCost = shippingCost;
        this.deliveryDate = deliveryDate;
        this.transactionId = transactionId;
    }

    public Order(Float price, Date orderDate, Float shippingCost, Date deliveryDate, Integer transactionId) {
        this.price = price;
        this.orderDate = orderDate;
        this.shippingCost = shippingCost;
        this.deliveryDate = deliveryDate;
        this.transactionId = transactionId;
    }

    public Order(Integer orderId){
        this.orderId = orderId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Float getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Float shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

}
