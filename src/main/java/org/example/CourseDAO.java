package org.example;

import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO implements StudentInterface<Course> {
    DatabaseConnection dbConnection = new DatabaseConnection();

    private Connection getConnection() throws SQLException {
        return dbConnection.connect();
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                courses.add(new Course(

                        rs.getString("course_name"),
                        rs.getString("course_description")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error fetching courses: " + e.getMessage());
        }

        return courses;
    }

    @Override
    public void create( Course course) {
        String query = "INSERT INTO courses (course_name, course_description) VALUES (?, ?)";

        try (Connection conn = getConnection(); PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, course.getName());
            pst.setString(2, course.getDescription());

            System.out.println("Course added successfully.");
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public Course read(int id) {
        String query = "SELECT * FROM courses WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Course(
                        rs.getString("course_name"),
                        rs.getString("course_description")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error reading course: " + e.getMessage());
        }

        return null;
    }

    @Override
    public void update(Course course) {
        String query= "UPDATE Courses SET course_name = ?, course_description = ? WHERE id = ?";


        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setString(1, course.getName());
            pst.setString(2, course.getDescription());
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Course updated successfully.");
            } else {
                System.out.println("Update failed. No course found with ID: " + course.getName());
            }

        } catch (SQLException e) {
            System.out.println("Error updating course: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM courses WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {

            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Course deleted successfully.");
            } else {
                System.out.println("No course found with ID: " + id);
            }

        } catch (SQLException e) {
            System.out.println("Error deleting course: " + e.getMessage());
        }
    }
}
