package controllers;

import services.RevenueService;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class RevenueController {
    private RevenueService revenueService;

    public RevenueController(){
        revenueService = new RevenueService();
    }

    public Map<String,Integer> getTotalRevenuePerLocation(Date startDate, Date endDate){
        return revenueService.getTotalRevenuePerLocation(startDate,endDate);
    }

    public  Map<Integer,Map<String,Integer>> getTotalRevenuePerDistributor(Date startDate, Date endDate) {
        return revenueService.getTotalRevenuePerDistributor(startDate,endDate);
    }

    public int getTotalRevenue(Date startDate, Date endDate) {
        return revenueService.getTotalRevenue(startDate,endDate);
    }

    public long getTotalExpense(Date startDate, Date endDate){
        return revenueService.getTotalExpense(startDate,endDate);
    }

    public Map<String, Integer> getTotalRevenuePerCity(Date startDate, Date endDate){
        return revenueService.getTotalRevenuePerCity(startDate,endDate);
    }

    public Map<String, List<List<Object>>> getPerDistributorPerPublicationPrice(){
        return revenueService.getPerDistributorPerPublicationPrice();
    }

    public List<Integer> getPaymentsPerWorkType(){
        return revenueService.getPaymentsPerWorkType();
    }
}
