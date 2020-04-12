package controllers;

import entity.Order;
import entity.OrderPayment;
import services.OrderPaymentService;

import java.util.List;

public class OrderPaymentController {
    private OrderPaymentService orderPaymentService;

    public OrderPaymentController(){
        orderPaymentService = new OrderPaymentService();
    }

    public Order deleteTransactionsForOrders(List<Order> orders){
        for(Order o : orders){
            if(!orderPaymentService.deleteTransactionForOrder(o)){
                return o;
            }
        }
        return null;
    }

    public boolean createOrderPayment(OrderPayment orderPayment) {
        return orderPaymentService.createOrderPayment(orderPayment);
    }
}
