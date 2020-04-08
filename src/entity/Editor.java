package entity;

public class Editor {
    private Employee employee;

    public Editor(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return this.employee.toString();
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


}
