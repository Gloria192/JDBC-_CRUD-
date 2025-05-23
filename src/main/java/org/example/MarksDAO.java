package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static javax.management.remote.JMXConnectorFactory.connect;

public class MarksDAO implements StudentInterface<Marks> {
    DatabaseConnection dbConnection = new DatabaseConnection();

    private Connection getConnection() throws SQLException {
        return dbConnection.connect();
    }

    @Override
    public List<Marks> findAll(int Id) {
        List<Marks> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection conn = getConnection();
             Statement stst = conn.createStatement();
             ResultSet rs = stst.executeQuery(query)) {

            while (rs.next()) {
                students.add(new Marks(
                        rs.getInt("Student_id"),
                        rs.getInt("Course_Id"),
                        rs.getFloat("Marks")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching students: " + e.getMessage());
        }

        return List.of();
    }


    @Override
    public void create(Marks marks) {
        String query = "INSERT INTO marks(Student_Id,Course_Id, Marks) VALUES (?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, marks.getStudent_Id());
            pst.setInt(2, marks.getCourse_Id());
            pst.setFloat(3, marks.getMarks());

            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student marks added successfully.");
            } else {
                System.out.println("Failed to add student marks.");
            }

        } catch (SQLException e) {
            System.out.println("Error adding marks: " + e.getMessage());
        }
    }


    @Override
    public Marks read(int id) {
        return null;
    }

    @Override
    public void update(Marks marks) {
        String query = "UPDATE Marks SET marks = ? WHERE student_id = ? AND course_id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

             // Corrected column reference
            ps.setInt(1, marks.getStudent_Id());
            ps.setInt(2, marks.getCourse_Id());
            ps.setFloat(3, marks.getMarks());

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Marks updated successfully for Student ID: " + marks.getStudent_Id());
            } else {
                System.out.println(" Update failed. No matching record found.");
            }

        } catch (SQLException e) {
            System.err.println("rror updating marks: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM students WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("marks deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    }


