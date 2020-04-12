package controllers;

import services.PeriodicityService;

import java.util.List;

public class PeriodicityController {
    private PeriodicityService periodicityService;

    public PeriodicityController(){
        periodicityService = new PeriodicityService();
    }

    public List<Object> getAllPeriodicities(){
        return periodicityService.getAllPeriodicities();
    }

}
