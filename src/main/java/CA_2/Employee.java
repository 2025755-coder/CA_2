/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author tina
 */

import java.util.Locale;

public class Employee {
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String email;
    private final double salary;
    private final Department department;
    private final Manager manager;
    private final String company;

    public Employee(String firstName, String lastName, String gender, String email, double salary,
                    String department, String position, String jobTitle, String company) {
        this.firstName = safe(firstName);
        this.lastName = safe(lastName);
        this.gender = safe(gender);
        this.email = safe(email);
        this.salary = salary;
        this.department = new Department(department);
        this.manager = new Manager(position, jobTitle);
        this.company = safe(company);
    }

    public static Employee fromManualEntry(String fullName, String managerRole, String department) {
        String trimmedName = fullName.trim();
        String firstName = trimmedName;
        String lastName = "";

        int lastSpace = trimmedName.lastIndexOf(' ');
        if (lastSpace > 0) {
            firstName = trimmedName.substring(0, lastSpace).trim();
            lastName = trimmedName.substring(lastSpace + 1).trim();
        }

        return new Employee(firstName, lastName, "", "", 0.0, department, managerRole, managerRole, "Manual Entry");
    }

    public String getFullName() {
        if (lastName.isBlank()) {
            return firstName;
        }
        return firstName + " " + lastName;
    }

    public String getSearchKey() {
        return getFullName().toLowerCase(Locale.ROOT);
    }

    public String getRole() {
        return manager.getRole();
    }

    public String getDepartment() {
        return department.getName();
    }

    public String getEmail() {
        return email;
    }

    public String getCompany() {
        return company;
    }

    public String getGender() {
        return gender;
    }

    public double getSalary() {
        return salary;
    }

    private static String safe(String value) {
        return value == null ? "" : value.trim();
    }
}
