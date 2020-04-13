package controllers;

import entity.Address;
import entity.Distributor;
import services.AddressService;

import java.util.List;

public class AddressController {
    private AddressService addressService;

    public AddressController(){
        this.addressService = new AddressService();
    }

    public boolean addAddressForDistributor(Address address){
        return addressService.addAddressForDistributor(address);
    }

    public boolean deleteAddressForDistributor(Distributor distributor) {
        return addressService.deleteAddressForDistributor(distributor);
    }

    public List<Object> getAddressForDistributor(Distributor currentDistributor) {
        return addressService.getAddressForDistributor(currentDistributor);
    }

    public boolean updateAddressForDistributor(Address address, String oldLocation){
        return addressService.updateAddressForDistributor(address, oldLocation);
    }
}
