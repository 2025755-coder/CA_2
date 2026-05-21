/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author tina
 */

public enum MenuOption {
    SORT(1, "SORT"),
    SEARCH(2, "SEARCH"),
    ADD_RECORDS(3, "ADD RECORDS"),
    CREATE_BINARY_TREE(4, "Create Binary Tree"),
    EXIT(5, "EXIT");

    private final int choice;
    private final String label;

    MenuOption(int choice, String label) {
        this.choice = choice;
        this.label = label;
    }

    public int getChoice() {
        return choice;
    }

    public String getLabel() {
        return label;
    }

    public static MenuOption fromChoice(int choice) {
        for (MenuOption option : values()) {
            if (option.choice == choice) {
                return option;
            }
        }
        return null;
    }
}