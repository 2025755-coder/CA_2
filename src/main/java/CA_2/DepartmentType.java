/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author tina
 */

public enum DepartmentType {
    CUSTOMER_SERVICE(1, "Customer Service"),
    FOREIGN_EXCHANGE(2, "Foreign Exchange"),
    HR(3, "HR"),
    FINANCE(4, "Finance"),
    IT_DEVELOPMENT(5, "IT Development"),
    MARKETING(6, "Marketing"),
    ACCOUNTING(7, "Accounting"),
    OPERATIONS(8, "Operations"),
    SALES(9, "Sales"),
    TECHNICAL_SUPPORT(10, "Technical Support");

    private final int choice;
    private final String displayName;

    DepartmentType(int choice, String displayName) {
        this.choice = choice;
        this.displayName = displayName;
    }

    public int getChoice() {
        return choice;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static DepartmentType fromChoice(int choice) {
        for (DepartmentType department : values()) {
            if (department.choice == choice) {
                return department;
            }
        }
        return null;
    }
}
