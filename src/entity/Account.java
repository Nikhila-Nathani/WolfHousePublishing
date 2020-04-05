package entity;

public class Account {

    private Employee employee;

    public Account(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Employee Id:\t "+employee.getEmployeeId()+"\t Employee Name:\t"+employee.getEmployeeName();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


}
