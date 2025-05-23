package org.example;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentsDAO studentsDAO = new StudentsDAO();
        CourseDAO courseDAO = new CourseDAO();

        try {
            boolean running = true; // ✅ Use a flag instead of while (true)
            while (running) {
                System.out.println("\nMenu:");
                System.out.println("a. Add Student");
                System.out.println("b. View Student");
                System.out.println("c. Update Student");
                System.out.println("d. Delete Student");
                System.out.println("e. Add course");
                System.out.println("f. View Courses");
                System.out.println("g. Update course");
                System.out.println("h. Delete Course");
                System.out.println("i. Add Mark [student id, course id, marks]");
                System.out.println("j. View Student Marks [student_id]");
                System.out.println("k. Update Mark [student id, course id, marks]");
                System.out.println("l. Delete Student Mark in a course");
                System.out.println("m.Exit........");

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

                        student = new Students(id, firstName, lastName, email, birth);
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
                        System.out.println("Enter course name:");
                        String updated_course_name = scanner.nextLine();
                        System.out.println("Enter description:");
                        String course_description = scanner.nextLine();
                        Course newCourse = new Course(0, updated_course_name,course_description);
                        courseDAO.create(newCourse);
                        break;
                    case "f":
                        List<Course> courses = courseDAO.findAll(0);
                        System.out.println("Fetched courses count: " + courses.size());

                        if (courses.isEmpty()) {
                            System.out.println("No courses available.");
                        } else {
                            for (Course c : courses) {
                                System.out.println("Course ID: " + c.getName() + "  " + c.getDescription());
                            }
                        }
                        break;

                    case "g":
                        System.out.println("Enter Course ID to update:");
                        int courseId = Integer.parseInt(scanner.nextLine()); // Ensure an ID is provided
                        System.out.println("Enter new course name:");
                        String course_name = scanner.nextLine();

                        System.out.println("Enter new description:");
                        course_description = scanner.nextLine();

                        // Create Course object with ID for update
                        Course updatedCourse = new Course(courseId, course_name, course_description);

                        // Perform update operation
                        courseDAO.update(updatedCourse);
                        break;

                    case "h":
                        System.out.println("Enter Course ID to delete:");
                        id = Integer.parseInt(scanner.nextLine());
                        courseDAO.delete(id);
                        System.out.println("Student deleted successfully.");
                        break;

//                    case "i":
////                        System.out.println("Enter Course ID to update:");
////                        int id = Integer.parseInt(scanner.nextLine());
//
//                        System.out.println("Enter course name:");
//                        String course_name = scanner.nextLine();
//
//                        System.out.println("Enter course description:");
//                        String course_description = scanner.nextLine();
//
//                        Course updatedCourse = new Course(course_name, course_description);
//                        courseDAO.update(updatedCourse);
//
//                        System.out.println("Course updated successfully.");
//                        break;


                    case"i":
                        System.out.println("add yours marks");
                        float marks = Float.parseFloat(scanner.nextLine());
                        System.out.println("enter student id");
                        int studentId = Integer.parseInt(scanner.nextLine());
                        System.out.println("enter course id");
                        int courseId1 = Integer.parseInt(scanner.nextLine());
                        Marks newMarks = new Marks(studentId,courseId1,marks);
                        System.out.println("marks added successfully"+" "+newMarks);
                        break;
                    case "j":
                        MarksDAO marksDAO = new MarksDAO();
                        List<Marks> newmarks = marksDAO.findAll(0);
                        System.out.println("Fetched courses count: " + newmarks.size());

                        if (newmarks.isEmpty()) {
                            System.out.println("No courses available.");
                        } else {
                            for (Marks m : newmarks) {
                                System.out.println("Course ID: " + m.getStudent_Id() + "  "  + "  " + m.getMarks());
                            }
                        }
                    case "l":
                        System.out.println("Enter studend ID to delete:");
                        studentId = Integer.parseInt(scanner.nextLine());
                        studentsDAO.delete(studentId);
                        System.out.println("Student deleted successfully.");
                        break;

                    case "m":
                        System.out.println("Exiting...");
                        System.exit(0);

                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
        } finally {
            scanner.close();
        }
    }
}
