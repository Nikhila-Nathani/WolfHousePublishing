package entity;

public class Employee {
    private Integer employeeId;
    private String employeeName;
    private Float pay;
    private Integer periodicity;

    public Employee(Integer employeeId, String employeeName, Float pay, Integer periodicity) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.pay = pay;
        this.periodicity = periodicity;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Float getPay() {
        return pay;
    }

    public void setPay(Float pay) {
        this.pay = pay;
    }

    public Integer getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Integer periodicity) {
        this.periodicity = periodicity;
    }

    @Override
    public String toString() {
        return "\t"+employeeId+"\t"+employeeName+"\t"+pay+"\t"+periodicity;
    }
}
