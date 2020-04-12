package controllers;

import entity.Order;
import entity.OrderContains;
import services.OrderContainsService;

import java.util.List;

public class OrderContainsController {
    private OrderContainsService orderContainsService;

    public OrderContainsController(){
        orderContainsService = new OrderContainsService();
    }

    public Order deleteOrderContainsForOrder(List<Order> orders){
        for(Order o : orders){
            if(!orderContainsService.deleteOrderContainsForOrder(o)){
                return o;
            }
        }
        return null;
    }

    public OrderContains createORderContainsForORder(List<OrderContains> orderContains){
        for(OrderContains oc  : orderContains){
            if(!orderContainsService.createOrderContains(oc)){
                return oc;
            }
        }
        return null;
    }
}
