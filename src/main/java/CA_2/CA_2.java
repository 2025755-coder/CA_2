/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package CA_2;

/**
 *
 * @author tina
 */

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CA_2 {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final List<Employee> EMPLOYEES = new ArrayList<>();
    private static final List<Employee> MANUALLY_ADDED_EMPLOYEES = new ArrayList<>();

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

    private static void loadInitialData() {
        String fileName = readLine("Please enter the filename to read (press Enter for default): ").trim();
        if (fileName.isBlank()) {
            fileName = "Applicants_Form.txt";
        }

        Path resolvedFile = resolveInputFile(fileName);
        if (resolvedFile == null) {
            System.out.println("File not found. The program will start with an empty list.");
            return;
        }

        try {
            List<String> lines = Files.readAllLines(resolvedFile, StandardCharsets.UTF_8);
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (line.isEmpty() || line.toLowerCase(Locale.ROOT).startsWith("first name,")) {
                    continue;
                }

                String[] parts = line.split(",", -1);
                if (parts.length < 9) {
                    continue;
                }

                EMPLOYEES.add(new Employee(
                        parts[0].trim(),
                        parts[1].trim(),
                        parts[2].trim(),
                        parts[3].trim(),
                        parseDouble(parts[4].trim()),
                        parts[5].trim(),
                        parts[6].trim(),
                        parts[7].trim(),
                        parts[8].trim()
                ));
            }

            System.out.println("File read successfully: " + resolvedFile.getFileName());
            System.out.println("Records loaded: " + EMPLOYEES.size());
        } catch (IOException ex) {
            System.out.println("Unable to read file: " + ex.getMessage());
        }
    }

    private static Path resolveInputFile(String fileName) {
        List<String> candidates = new ArrayList<>();
        candidates.add(fileName);
        candidates.add("Applicants_Form.txt");
        candidates.add("Applicants_Form - Sample data file for read.txt");
        candidates.add("../Applicants_Form.txt");
        candidates.add("../Applicants_Form - Sample data file for read.txt");
        candidates.add("../../Applicants_Form.txt");
        candidates.add("../../Applicants_Form - Sample data file for read.txt");

        for (String candidate : candidates) {
            Path path = Paths.get(candidate);
            if (Files.exists(path)) {
                return path;
            }
        }

        return null;
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("Do You wish to SORT or SEARCH:");
        for (MenuOption option : MenuOption.values()) {
            System.out.println(option.getChoice() + ". " + option.getLabel());
        }
    }

    private static MenuOption readMenuOption() {
        while (true) {
            String input = readLine("Choose an option: ").trim();
            try {
                int choice = Integer.parseInt(input);
                MenuOption option = MenuOption.fromChoice(choice);
                if (option != null) {
                    return option;
                }
            } catch (NumberFormatException ignored) {
            }

            System.out.println("Invalid menu option. Please try again.");
        }
    }

    private static void sortAndDisplayEmployees() {
        if (EMPLOYEES.isEmpty()) {
            System.out.println("No records available to sort.");
            return;
        }

        List<Employee> sortedEmployees = mergeSort(new ArrayList<>(EMPLOYEES));
        System.out.println("SORT selected");
        System.out.println("First 20 names in alphabetical order:");

        int limit = Math.min(20, sortedEmployees.size());
        for (int i = 0; i < limit; i++) {
            Employee employee = sortedEmployees.get(i);
            System.out.println((i + 1) + ". " + employee.getFullName());
        }
    }

    private static void searchEmployeeByName() {
        if (EMPLOYEES.isEmpty()) {
            System.out.println("No records available to search.");
            return;
        }

        List<Employee> sortedEmployees = mergeSort(new ArrayList<>(EMPLOYEES));
        String query = readLine("Enter the name to search: ").trim();

        if (query.isBlank()) {
            System.out.println("Search text cannot be empty.");
            return;
        }

        Employee found = binarySearch(sortedEmployees, query.toLowerCase(Locale.ROOT), 0, sortedEmployees.size() - 1);
        if (found == null) {
            System.out.println("No matching record found for: " + query);
            return;
        }

        System.out.println("Record found:");
        System.out.println("Name: " + found.getFullName());
        System.out.println("Manager Type / Role: " + found.getRole());
        System.out.println("Department: " + found.getDepartment());
        if (!found.getEmail().isBlank()) {
            System.out.println("Email: " + found.getEmail());
        }
        if (!found.getCompany().isBlank()) {
            System.out.println("Company: " + found.getCompany());
        }
    }

    private static void addEmployeeRecord() {
        System.out.println("Please select an option from the following:");
        System.out.println("1. Add Employee");
        System.out.println("2. Generate employee hierarchy binary tree");

        String choiceInput = readLine("Choose an option: ").trim();
        if (!"1".equals(choiceInput)) {
            System.out.println("Returning to the menu.");
            return;
        }

        String name = readLine("Please input the Employee Name: ").trim();
        if (name.isBlank()) {
            System.out.println("Name cannot be empty.");
            return;
        }

        ManagerType managerType = promptManagerType();
        DepartmentType department = promptDepartmentType();

        Employee employee = Employee.fromManualEntry(name, managerType.getDisplayName(), department.getDisplayName());
        EMPLOYEES.add(employee);
        MANUALLY_ADDED_EMPLOYEES.add(employee);

        System.out.println("\"" + employee.getFullName() + "\" has been added as \"" + managerType.getDisplayName()
                + "\" to \"" + department.getDisplayName() + "\" successfully!");
        displayManuallyAddedEmployees();
    }

    private static ManagerType promptManagerType() {
        System.out.println("Please select from the following Management Staff:");
        for (ManagerType type : ManagerType.values()) {
            System.out.println(type.getChoice() + ". " + type.getDisplayName());
        }

        while (true) {
            String input = readLine("Select manager type: ").trim();
            try {
                int choice = Integer.parseInt(input);
                ManagerType type = ManagerType.fromChoice(choice);
                if (type != null) {
                    return type;
                }
            } catch (NumberFormatException ignored) {
            }

            System.out.println("Invalid manager type. Please try again.");
        }
    }

    private static DepartmentType promptDepartmentType() {
        System.out.println("Please select the Department:");
        for (DepartmentType department : DepartmentType.values()) {
            System.out.println(department.getChoice() + ". " + department.getDisplayName());
        }

        while (true) {
            String input = readLine("Select department: ").trim();
            try {
                int choice = Integer.parseInt(input);
                DepartmentType department = DepartmentType.fromChoice(choice);
                if (department != null) {
                    return department;
                }
            } catch (NumberFormatException ignored) {
            }

            System.out.println("Invalid department. Please try again.");
        }
    }

    private static void buildAndShowHierarchyTree() {
        if (EMPLOYEES.isEmpty()) {
            System.out.println("No records available to build a tree.");
            return;
        }

        int recordCount = EMPLOYEES.size();
        if (recordCount < 20) {
            System.out.println("Only " + recordCount + " records are available. At least 20 are required for full assessment.");
        }

        BinaryTree tree = new BinaryTree();
        for (int i = 0; i < recordCount; i++) {
            tree.insertLevelOrder(EMPLOYEES.get(i));
        }

        System.out.println("Employee hierarchy using level-order insertion:");
        tree.printLevelOrder();
        System.out.println("Tree height: " + tree.height());
        System.out.println("Total node count: " + tree.countNodes());
    }

    private static void displayManuallyAddedEmployees() {
        System.out.println("Newly added records:");
        for (int i = 0; i < MANUALLY_ADDED_EMPLOYEES.size(); i++) {
            Employee employee = MANUALLY_ADDED_EMPLOYEES.get(i);
            System.out.println((i + 1) + ". " + employee.getFullName() + " | "
                    + employee.getRole() + " | " + employee.getDepartment());
        }
    }

    private static List<Employee> mergeSort(List<Employee> employees) {
        if (employees.size() <= 1) {
            return employees;
        }

        int middle = employees.size() / 2;
        List<Employee> left = mergeSort(new ArrayList<>(employees.subList(0, middle)));
        List<Employee> right = mergeSort(new ArrayList<>(employees.subList(middle, employees.size())));

        return merge(left, right);
    }

    private static List<Employee> merge(List<Employee> left, List<Employee> right) {
        List<Employee> merged = new ArrayList<>(left.size() + right.size());
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            Employee leftEmployee = left.get(leftIndex);
            Employee rightEmployee = right.get(rightIndex);

            if (leftEmployee.getSearchKey().compareTo(rightEmployee.getSearchKey()) <= 0) {
                merged.add(leftEmployee);
                leftIndex++;
            } else {
                merged.add(rightEmployee);
                rightIndex++;
            }
        }

        while (leftIndex < left.size()) {
            merged.add(left.get(leftIndex++));
        }

        while (rightIndex < right.size()) {
            merged.add(right.get(rightIndex++));
        }

        return merged;
    }

    private static Employee binarySearch(List<Employee> employees, String searchKey, int left, int right) {
        if (left > right) {
            return null;
        }

        int middle = left + (right - left) / 2;
        Employee middleEmployee = employees.get(middle);
        int comparison = middleEmployee.getSearchKey().compareTo(searchKey);

        if (comparison == 0) {
            return middleEmployee;
        }

        if (comparison > 0) {
            return binarySearch(employees, searchKey, left, middle - 1);
        }

        return binarySearch(employees, searchKey, middle + 1, right);
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine();
    }

    private static double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException ex) {
            return 0.0;
        }
    }
}
