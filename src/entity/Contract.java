package entity;

import java.sql.Date;

public class Contract {

    private Integer contractId;
    private Date startDate;
    private Date endDate;
    private Float pay;
    private Employee employee;

    public Contract(Integer contractId, Date startDate, Date endDate, Float pay, Employee employee) {
        this.contractId = contractId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pay = pay;
        this.employee = employee;
    }

    public Integer getContractId() {
        return contractId;
    }

    public void setContractId(Integer contractId) {
        this.contractId = contractId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Float getPay() {
        return pay;
    }

    public void setPay(Float pay) {
        this.pay = pay;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Contract Id:\t" + contractId +
                "\tStart Date:\t" + startDate +
                "\tEnd Date:\t" + endDate +
                "\tPay:\t" + pay +
                "\tEmployee Id:\t" + employee.getEmployeeId() +
                "\tEmployee Name:\t" + employee.getEmployeeName() ;

    }
}
