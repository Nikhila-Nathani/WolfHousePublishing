package entity;

public class EmployeePayment {
    private Employee employee;
    private Transaction transaction;

    public EmployeePayment(Employee employee, Transaction transaction) {
        this.employee = employee;
        this.transaction = transaction;
    }

    public EmployeePayment(){

    }
    @Override
    public String toString() {
        return "Name : \t"+employee.getEmployeeName()+"\tAmount : \t"+transaction.getAmount()+"\tDate of Payment : \t"+
                transaction.getDateOfTransaction();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }


}
