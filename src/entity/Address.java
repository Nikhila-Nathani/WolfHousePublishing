package entity;

public class Address {

    private Integer distributorId;
    private String location;
    private String contactPerson;
    private String city;

    public Address(Integer distributorId, String location, String contactPerson, String city) {
        this.distributorId = distributorId;
        this.location = location;
        this.contactPerson = contactPerson;
        this.city = city;
    }

    @Override
    public String toString() {
        return "\t"+distributorId+"\t" + location + "\t" + city +"\t" + contactPerson ;
    }

    public Integer getDistributorId() {
        return distributorId;
    }

    public void setDistributorId(Integer distributorId) {
        this.distributorId = distributorId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
