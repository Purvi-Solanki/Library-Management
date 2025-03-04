package models;

public class Librarian extends Person {
    private String employeeID;

    public Librarian(String name, String contactInfo, String employeeID) {
        super(name, contactInfo);
        this.employeeID = employeeID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Employee ID: " + employeeID);
    }
}
