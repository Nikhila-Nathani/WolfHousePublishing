package controllers;

import entity.Distributor;
import services.DistributorService;

import java.util.List;

public class DistributorController {
    private DistributorService distributorService;

    public DistributorController(){
        this.distributorService = new DistributorService();
    }

    public int createDistributor(Distributor distributor){
        return distributorService.createDistributorAndGetId(distributor);
    }

    public List<Object> getAllDistributors(){
        return distributorService.getAllDistributors();
    }

    public boolean updateDistributor(Distributor currentDistributor) {
        return distributorService.updateDistributor(currentDistributor);
    }

    public boolean deleteDistributor(Distributor distributor) {
        return distributorService.deleteDistributor(distributor);
    }

    public int getTotalDistributors() {
        return distributorService.getTotalDistributors();
    }

    public boolean updateBalance(Distributor currentDistributor) {
        return distributorService.updateBalance(currentDistributor);
    }

    public Distributor getDistributorById(int distributorId) {
        return distributorService.getDistributorById(distributorId);
    }
}
