import java.io.FileWriter; // Importing FileWriter library to create text files
import java.io.IOException; // Importing IOException class for error handling
import java.util.HashMap; // Importing HashMap class to store student grades
import java.util.List; // Importing List interface to store multiple grades for a student
import java.util.ArrayList; // Importing ArrayList class to store multiple grades for a student
import java.util.Scanner; // Importing Scanner class for user input. Very weird approach!

public class StudentGradebook {
    public static void main(String[] args) {
        HashMap<String, List<Double>> gradebook = new HashMap<>(); // Creating a HashMap to store student names as keys and lists of grades as values
        Scanner scanner = new Scanner(System.in); // Creating a Scanner object to get user input. Very unorthodox!
        int choice; // Variable to store user's menu choice

        // User interface inside of do-while loop for basic error handling
        do {
            System.out.println("\nWelcome to Student Gradebook");
            System.out.println("1. Add Student Grade");
            System.out.println("2. Update Student Grade");
            System.out.println("3. Calculate Average Grade");
            System.out.println("4. Export Grades to Text File"); // Attempt of Stretch by exporting a text file.
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            // Validating user input to ensure it's an integer
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); 
            }
            choice = scanner.nextInt(); 

            // Handling user's menu choice using a switch statement. Very similar to first module
            switch (choice) {
                case 1:
                    addStudentGrade(scanner, gradebook); // Call addStudentGrade method to add a student's grade
                    break;
                case 2:
                    updateStudentGrade(scanner, gradebook); // Call updateStudentGrade method to update a student's grade
                    break;
                case 3:
                    calculateAverageGrade(scanner, gradebook); // Call calculateAverageGrade method to calculate the average grade
                    break;
                case 4:
                    exportGrades(gradebook); // Call exportGrades method to export grades to a text file.Stretch.
                    break;
                case 5:
                    System.out.println("Exiting program..."); // Displaying exit message
                    break;
                default:
                    System.out.println("Invalid choice, please try again."); // Displaying message for invalid choice
            }
        } while (choice != 5); // Continue the loop until the user chooses to exit
    }

    // Method to add a student's grade to the gradebook
    private static void addStudentGrade(Scanner scanner, HashMap<String, List<Double>> gradebook) {
        System.out.print("Enter student name: ");
        String studentName = scanner.next(); // Receiving student's name
        System.out.print("Enter grade for " + studentName + ": ");
        double grade = scanner.nextDouble(); // Receiving student's grade

        // Check if the student already exists in the gradebook
        if (gradebook.containsKey(studentName)) {
            // If the student exists, add the new grade to the list of grades for the student
            gradebook.get(studentName).add(grade);
        } else {
            // If the student doesn't exist, create a new list with the grade and add it to the gradebook
            List<Double> grades = new ArrayList<>();
            grades.add(grade);
            gradebook.put(studentName, grades);
        }
        
        System.out.println("Grade added successfully."); // Displaying success message
    }

 // Method to update a student's grade in the gradebook
    private static void updateStudentGrade(Scanner scanner, HashMap<String, List<Double>> gradebook) {
        System.out.print("Enter student name: ");
        String studentName = scanner.next(); // Checking student's name
        if (gradebook.containsKey(studentName)) { // Checking if student exists in the gradebook
            List<Double> grades = gradebook.get(studentName); // Retrieve the list of grades for the student
            System.out.print("Enter new grade for " + studentName + ": ");
            double newGrade = scanner.nextDouble(); // Receiving new grade

            // Prompt the user to confirm if they want to update the grade
            System.out.print("Are you sure you want to update " + studentName + "'s grade? (Y/N): ");
            String confirmation = scanner.next().toUpperCase(); // Convert input to uppercase for case-insensitive comparison

            if (confirmation.equals("Y")) { // If the user confirms the update
                grades.clear(); // Remove all existing grades
                grades.add(newGrade); // Add the new grade
                System.out.println("Grade updated successfully."); // Display success message
            } else {
                System.out.println("Grade update canceled."); // Display message if the user cancels the update
            }
        } else {
            System.out.println("Student not found in gradebook."); // Displaying error message if student is not found
        }
    }

    // Method to calculate the average grade and display all grades for a specific student in the gradebook
    private static void calculateAverageGrade(Scanner scanner, HashMap<String, List<Double>> gradebook) {
        if (gradebook.isEmpty()) { // Checking if gradebook is empty
            System.out.println("Gradebook is empty."); // Displaying message if gradebook is empty
            return;
        }

        System.out.print("Enter student name: ");
        String studentName = scanner.next(); // Prompting the user to enter the student's name
        if (gradebook.containsKey(studentName)) {
            List<Double> grades = gradebook.get(studentName); // Retrieve the list of grades for the student
            double total = 0; // Variable to store total grades

            System.out.println("Grades for " + studentName + ":");
            for (Double grade : grades) {
                System.out.println(" - " + grade); // Displaying each grade for the student
                total += grade; // Adding grade to total
            }

            double average = total / grades.size(); // Calculating average grade
            System.out.println("Average grade for " + studentName + ": " + average); // Displaying average grade
        } else {
            System.out.println("Student not found in gradebook."); // Displaying error message if student is not found
        }
    }

    // Method to export grades to a text file. This is the Stretch.
    private static void exportGrades(HashMap<String, List<Double>> gradebook) {
        if (gradebook.isEmpty()) { // Checking if gradebook is empty
            System.out.println("Gradebook is empty. Nothing to export."); // Displaying message if gradebook is empty
            return; // Exiting the method
        }

        try {
            FileWriter writer = new FileWriter("grades.txt"); // Creating a FileWriter object to write to a text file
            for (String studentName : gradebook.keySet()) {
                List<Double> grades = gradebook.get(studentName); // Retrieve the list of grades for the student
                for (Double grade : grades) {
                    writer.write(studentName + ": " + grade + "\n"); // Adding each student's name and grade to the text file
                }
            }
            writer.close(); // Closing the FileWriter
            System.out.println("Grades exported to grades.txt successfully."); // Displaying success message
        } catch (IOException e) {
            System.out.println("An error occurred while exporting grades: " + e.getMessage()); // Displaying error message if file writing fails
        }
    }
}