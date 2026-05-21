/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author tina
 */

public class Department {
    private final String name;

    public Department(String name) {
        this.name = safe(name);
    }

    public String getName() {
        if (name.isBlank()) {
            return "Unknown";
        }
        return name;
    }

    private static String safe(String value) {
        return value == null ? "" : value.trim();
    }
}
