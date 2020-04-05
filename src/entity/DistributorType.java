package entity;

public class DistributorType {
    private Integer distributorTypeId;
    private String distributorTypeName;

    public DistributorType(Integer distributorTypeId, String distributorTypeName) {
        this.distributorTypeId = distributorTypeId;
        this.distributorTypeName = distributorTypeName;
    }

    public Integer getDistributorTypeId() {
        return distributorTypeId;
    }

    public void setDistributorTypeId(Integer distributorTypeId) {
        this.distributorTypeId = distributorTypeId;
    }

    public String getDistributorTypeName() {
        return distributorTypeName;
    }

    public void setDistributorTypeName(String distributorTypeName) {
        this.distributorTypeName = distributorTypeName;
    }

    @Override
    public String toString() {
        return "Id : \t" + distributorTypeId + "\tName : \t" + distributorTypeName;
    }
}
