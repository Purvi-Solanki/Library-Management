package src.models;

public class Person {
    protected String name;
    protected String contactInfo;

    public Person(String name, String contactInfo) {
        this.name = name;
        this.contactInfo = contactInfo;
    }

    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Contact Info: " + contactInfo);
    }
}
