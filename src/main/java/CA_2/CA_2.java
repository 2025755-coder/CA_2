/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package CA_2;

/**
 *
 * @author tina
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class CA_2 {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Employee> EMPLOYEES = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("CA_2 Organisation Prototype");
        System.out.println("----------------------------------------");

        loadInitialData();

        boolean running = true;
        while (running) {
            printMenu();
            MenuOption option = readMenuOption();

            switch (option) {
                case SORT -> sortAndDisplayEmployees();
                case SEARCH -> searchEmployeeByName();
                case ADD_RECORDS -> addEmployeeRecord();
                case CREATE_BINARY_TREE -> buildAndShowHierarchyTree();
                case EXIT -> running = false;
            }
        }

        System.out.println("Program ended.");
    }