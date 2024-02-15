package entities;

import java.sql.*;

public class CourseDATest {
    private Connection conn;
    private CourseDA courseDao;

    public CourseDATest() throws SQLException {
        // Set up the database connection and the DAO
        String conString = "jdbc:postgresql://localhost:5432/AMS";
        conn = DriverManager.getConnection(conString, "postgres", "qwertyzsdv");
        courseDao = new CourseDA(conn);
    }

    public void testSaveAndFind() throws SQLException {
        // Create a course
        Course course = new Course(0, "Test Course", "This is a test course.");

        // Save the course
        courseDao.save(course);
        System.out.println("Saved course: " + course);

        // Retrieve the course
        Course retrievedCourse = courseDao.find(course.getId());
        System.out.println("Retrieved course: " + retrievedCourse);

        // Check that the retrieved course is not null and has the expected properties
        if (retrievedCourse != null) {
            System.out.println("Retrieved course is not null");
            if (course.getName().equals(retrievedCourse.getName()) &&
                    course.getDescription().equals(retrievedCourse.getDescription())) {
                System.out.println("Retrieved course has the expected properties");
            } else {
                System.out.println("Retrieved course does not have the expected properties");
            }
        } else {
            System.out.println("Retrieved course is null");
        }
    }

    public void closeConnection() throws SQLException {
        // Close the database connection
        if (conn != null) {
            conn.close();
        }
    }
}

