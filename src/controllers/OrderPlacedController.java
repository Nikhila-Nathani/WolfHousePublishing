package controllers;

import entity.Distributor;
import entity.Order;
import entity.OrderPlaced;
import services.OrderPlacedService;

import java.util.List;

public class OrderPlacedController {
    private static OrderPlacedService orderPlacedService;

    public OrderPlacedController(){
        orderPlacedService = new OrderPlacedService();
    }

    public List<Order> getOrdersForDistributor(Distributor distributor){
        return orderPlacedService.getOrdersForDistributor(distributor);
    }

    public boolean deleteOrdersPlacesForDistributor(Distributor distributor){
        return orderPlacedService.deleteOrdersPlacesForDistributor(distributor);
    }

    public boolean createOrderPlaced(OrderPlaced orderPlaced) {
        return orderPlacedService.createOrderPlaced(orderPlaced);
    }

    public int getDistributorIdForOrder(Order order) {
        return orderPlacedService.getDistributorIdForOrder(order);
    }
}
