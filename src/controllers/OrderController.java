package controllers;

import entity.Order;
import services.OrderService;

import java.util.List;

public class OrderController {
    private OrderService orderService;

    public OrderController(){
        orderService = new OrderService();
    }

    public Order deleteOrders(List<Order> orders){
        for(Order o : orders){
            if(!orderService.deleteOrder(o)){
                return o;
            }
        }
        return null;
    }

    public Integer createOrder(Order order) {
        return orderService.createOrder(order);
    }

    public List<Object> getAllOrdersForPayment() {
        return orderService.getAllOrdersForPayment();
    }

    public List<Object> getAllPaidOrders() {
        return orderService.getAllPaidOrders();
    }
}