package controllers;

import services.PeriodicPublicationService;

import java.util.List;

public class PeriodicPublicationController {

    private PeriodicPublicationService periodicPublicationService = null;
    public PeriodicPublicationController() {
        periodicPublicationService = new PeriodicPublicationService();
    }

    public List<Object> getAllPeriodicPublications(){
        return periodicPublicationService.getAllPeriodicPublications();
    }
}
