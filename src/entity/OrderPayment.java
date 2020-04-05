package entity;

public class OrderPayment {
    private Order order;
    private Transaction transaction;

    public OrderPayment(Order order, Transaction transaction) {
        this.order = order;
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "OrderId : \t"+order.getOrderId()+"\tDate of Transaction :\t"+
                transaction.getDateOfTransaction()+"\tAmount : \t"+transaction.getAmount();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
