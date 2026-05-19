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
    private final String department;
    private final String position;
    private final String jobTitle;
    private final String company;

    public Employee(String firstName, String lastName, String gender, String email, double salary,
                    String department, String position, String jobTitle, String company) {
        this.firstName = safe(firstName);
        this.lastName = safe(lastName);
        this.gender = safe(gender);
        this.email = safe(email);
        this.salary = salary;
        this.department = safe(department);
        this.position = safe(position);
        this.jobTitle = safe(jobTitle);
        this.company = safe(company);
    }