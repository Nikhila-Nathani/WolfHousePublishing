package entity;

public class Distributor {
    private Integer distributorId;
    private String distributorName;
    private Long phoneNumber;
    private Integer balance;
    private String activeStatus;
    private DistributorType distributorType;


    public Distributor(Integer distributorId, String distributorName, Long phoneNumber,
                       Integer balance, String activeStatus, DistributorType distributorType) {
        this.distributorId = distributorId;
        this.distributorName = distributorName;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.activeStatus = activeStatus;
        this.distributorType = distributorType;
    }

    public Distributor(String distributorName, Long phoneNumber,
                       Integer balance, String activeStatus, DistributorType distributorType) {
        this.distributorName = distributorName;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.activeStatus = activeStatus;
        this.distributorType = distributorType;
    }

    @Override
    public String toString() {
        return "\t" + distributorId + "\t" + distributorName+ "\t" + phoneNumber
                + "\t" + balance+ "\t" + activeStatus
                + "\t" + distributorType.getDistributorTypeName();
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

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }
}
