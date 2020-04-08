package entity;

public class Author {

    private Employee employee;

    public Author(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "\t "+employee.getEmployeeId()+"\t\t"+employee.getEmployeeName();
    }
}
