/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author tina
 */
public enum ManagerType {
    HEAD_MANAGER(1, "Head Manager"),
    ASSISTANT_MANAGER(2, "Assistant Manager"),
    TEAM_LEAD(3, "Team Lead");

    private final int choice;
    private final String displayName;

    ManagerType(int choice, String displayName) {
        this.choice = choice;
        this.displayName = displayName;
    }

    public int getChoice() {
        return choice;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static ManagerType fromChoice(int choice) {
        for (ManagerType type : values()) {
            if (type.choice == choice) {
                return type;
            }
        }
        return null;
    }
}