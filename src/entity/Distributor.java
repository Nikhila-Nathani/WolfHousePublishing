package entity;

public class Distributor {
    private Integer distributorId;
    private String distributorName;
    private Integer phoneNumber;
    private Float balance;
    private String activeStatus;
    private DistributorType distributorType;

    public Distributor(Integer distributorId, String distributorName, Integer phoneNumber,
                       Float balance, String activeStatus, DistributorType distributorType) {
        this.distributorId = distributorId;
        this.distributorName = distributorName;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.activeStatus = activeStatus;
        this.distributorType = distributorType;
    }

    @Override
    public String toString() {
        return "Id : \t" + distributorId + "\tName : \t" + distributorName+ "\tPhone Number : \t" + phoneNumber
                + "\tBalance : \t" + balance+ "\tActive Status : \t" + activeStatus
                + "\tDistributor Type : \t" + distributorType.getDistributorTypeName();
    }

    public DistributorType getDistributorType() {
        return distributorType;
    }

    public void setDistributorType(DistributorType distributorType) {
        this.distributorType = distributorType;
    }


    public Integer getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Integer distributorId) {
        this.distributorId = distributorId;
    }

    public String getDistributorName() {
        return distributorName;
    }

    public void setDistributorName(String distributorName) {
        this.distributorName = distributorName;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }
}
