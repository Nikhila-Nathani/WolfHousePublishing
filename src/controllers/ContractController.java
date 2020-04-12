package controllers;

import entity.Contract;
import services.ContractService;

import java.sql.Date;
import java.util.List;

public class ContractController {
    private ContractService contractService;

    public ContractController(){
        contractService = new ContractService();
    }

    public List<Object> getContractsForDate(Date date){
        return contractService.getContractsForDate(date);
    }

    public void updateTransactionIdsForContracts(List<Contract> contracts) {
        for(Contract c: contracts){
            contractService.updateTransactionIdForContract(c);
        }
    }
}
