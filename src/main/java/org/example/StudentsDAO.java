package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentsDAO implements StudentInterface<Students> {
    DatabaseConnection dbConnection = new DatabaseConnection();

    private Connection getConnection() throws SQLException {
        return dbConnection.connect();
    }

    @Override
    public void create(Students student) {
        String query = "INSERT INTO students (first_name, last_name, email, date_of_birth) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getEmail());
            ps.setDate(4, Date.valueOf(student.getDateOfBirth()));
            ps.executeUpdate();

            // Retrieve generated ID
            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                student.setId(keys.getInt(1));
            }
            System.out.println("Student created successfully: " + student);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Students read(int id) {
        String query = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                LocalDate dob = rs.getDate("date_of_birth").toLocalDate();
                return new Students(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        dob
                );
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Students> findAll() {
        List<Students> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                LocalDate dob = rs.getDate("date_of_birth").toLocalDate();
                students.add(new Students(
                        rs.getInt("id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        dob
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return students;
    }

    @Override
    public void update(Students student) {
        String query = "UPDATE students SET first_name = ?, last_name = ?, email = ?, date_of_birth = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getLastName());
            ps.setString(3, student.getEmail());
            ps.setDate(4, Date.valueOf(student.getDateOfBirth()));
            ps.setInt(5, student.getId());
            ps.executeUpdate();
            System.out.println("Student updated successfully: " + student);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM students WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("Student deleted successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
