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
        candidates.add("Applicants_Form - Sample data file for read.txt");

        for (String candidate : candidates) {
            Path path = Paths.get(candidate);
            if (Files.exists(path)) {
                return path;
            }
        }

        return null;