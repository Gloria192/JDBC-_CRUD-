package org.example;
import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentsDAO studentsDAO = new StudentsDAO();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("a. Add Student");
            System.out.println("b. View Student");
            System.out.println("c. Update Student");
            System.out.println("d. Delete Student");
            System.out.println("e. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "a":
                    System.out.println("Enter first name:");
                    String firstName = scanner.nextLine();

                    System.out.println("Enter last name:");
                    String lastName = scanner.nextLine();

                    System.out.println("Enter email:");
                    String email = scanner.nextLine();

                    System.out.println("Enter date of birth (yyyy-MM-dd):");
                    LocalDate birthDate = LocalDate.parse(scanner.nextLine());

                    Students student = new Students(0, firstName, lastName, email, birthDate);
                    studentsDAO.create(student);
                    break;
                    case "b":
                    System.out.println("Enter Student ID:");
                    int id = Integer.parseInt(scanner.nextLine());

                    student = studentsDAO.read(id);
                    if (student != null) {
                        System.out.println("Student Details: " + student);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;


                case "c":
                    System.out.println("Enter Student ID to update:");
                    id = Integer.parseInt(scanner.nextLine());

                    System.out.println("Enter new first name:");
                    firstName = scanner.nextLine();

                    System.out.println("Enter new last name:");
                    lastName = scanner.nextLine();

                    System.out.println("Enter new email:");
                    email = scanner.nextLine();

                    System.out.println("Enter new date of birth (yyyy-MM-dd):");
                    LocalDate birth = LocalDate.parse(scanner.nextLine());

                    // ✅ Correctly assigning ID
                    student = new Students(id, firstName, lastName, email, birth);

                    // ✅ Updating student in database
                    studentsDAO.update(student);
                    System.out.println("Student updated successfully.");
                    break;

                case "d":
                    System.out.println("Enter Student ID to delete:");
                    id = Integer.parseInt(scanner.nextLine());
                    studentsDAO.delete(id);
                    System.out.println("Student deleted successfully.");
                    break;

                case "e":
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
