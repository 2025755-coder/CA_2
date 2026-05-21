/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author tina
 */

public class Manager {
    private final String position;
    private final String jobTitle;

    public Manager(String position, String jobTitle) {
        this.position = safe(position);
        this.jobTitle = safe(jobTitle);
    }

    public String getRole() {
        if (!jobTitle.isBlank()) {
            return jobTitle;
        }
        if (!position.isBlank()) {
            return position;
        }
        return "Unknown";
    }

    private static String safe(String value) {
        return value == null ? "" : value.trim();
    }
}
