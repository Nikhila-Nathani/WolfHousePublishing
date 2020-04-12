package controllers;

import entity.PeriodicPublication;
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

    public boolean createPeriodicPublication(PeriodicPublication periodicPublication){
        return periodicPublicationService.createPeriodicPublication(periodicPublication);
    }

    public boolean deletePeriodicPublication(PeriodicPublication periodicPublication) {
        return periodicPublicationService.deletePeriodicPublication(periodicPublication);
    }

    public boolean updatePeriodicPublication(PeriodicPublication periodicPublication) {
        return periodicPublicationService.updatePeriodicPublication(periodicPublication);
    }
}
