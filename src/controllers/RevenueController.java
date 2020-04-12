package controllers;

import services.RevenueService;

import java.util.List;
import java.util.Map;

public class RevenueController {
    private RevenueService revenueService;

    public RevenueController(){
        revenueService = new RevenueService();
    }

    public Map<String,Integer> getTotalRevenuePerLocation(){
        return revenueService.getTotalRevenuePerLocation();
    }

    public  Map<Integer,Map<String,Integer>> getTotalRevenuePerDistributor() {
        return revenueService.getTotalRevenuePerDistributor();
    }

    public int getTotalRevenue() {
        return revenueService.getTotalRevenue();
    }

    public int getTotalExpense(){
        return revenueService.getTotalExpense();
    }

    public Map<String, Integer> getTotalRevenuePerCity(){
        return revenueService.getTotalRevenuePerCity();
    }

    public Map<String, List<List<Object>>> getPerDistributorPerPublicationPrice(){
        return revenueService.getPerDistributorPerPublicationPrice();
    }

    public List<Integer> getPaymentsPerWorkType(){
        return revenueService.getPaymentsPerWorkType();
    }
}
