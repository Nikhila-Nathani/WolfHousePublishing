package entity;

public class Periodicity {
    private Integer periodicityId;
    private String periodicityName;

    public Periodicity(Integer periodicityId, String periodicityName) {
        this.periodicityId = periodicityId;
        this.periodicityName = periodicityName;
    }

    public Integer getPeriodicityId() {
        return periodicityId;
    }

    public void setPeriodicityId(Integer periodicityId) {
        this.periodicityId = periodicityId;
    }

    public String getPeriodicityName() {
        return periodicityName;
    }

    public void setPeriodicityName(String periodicityName) {
        this.periodicityName = periodicityName;
    }

    @Override
    public String toString() {
        return "\t" + periodicityId + "\t" + periodicityName;
    }
}
