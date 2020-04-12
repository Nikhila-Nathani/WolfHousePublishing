package entity;

import java.sql.Date;

public class Contract {

    private Integer contractId;
    private Date startDate;
    private Date endDate;
    private Integer pay;
    private Employee employee;
    private Integer transactionId;

    public Contract(Integer contractId, Date startDate, Date endDate, Integer pay, Employee employee,Integer transactionId) {
        this.contractId = contractId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pay = pay;
        this.employee = employee;
        this.transactionId = transactionId;
    }

    public Contract(Integer contractId, Date startDate, Date endDate, Integer pay, Employee employee) {
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

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setTransactionId(Integer transactionId){ this.transactionId = transactionId;}

    public Integer getTransactionId() {
        return transactionId;
    }

    @Override
    public String toString() {
        return "\t" + contractId +
                "\t" + startDate +
                "\t" + endDate +
                "\t" + pay +
                "\t" + employee.getEmployeeId() +
                "\t" + employee.getEmployeeName() ;
    }
}
