package controllers;

import services.DistributorTypeService;

import java.util.List;

public class DistributorTypeController {
    private DistributorTypeService distributorTypeService;

    public DistributorTypeController(){
        this.distributorTypeService = new DistributorTypeService();
    }

    public List<Object> getAllDistributorTypes(){
        return distributorTypeService.getAllDistributorTypes();
    }
}
