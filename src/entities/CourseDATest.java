package entities;

import java.sql.*;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter course details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();

        Course course = new Course(0, name, description);

        courseDao.save(course);
        System.out.println("Saved course: " + course);

        Course retrievedCourse = courseDao.find(course.getId());
        System.out.println("Retrieved course: " + retrievedCourse);

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

        scanner.close();
    }

    public void closeConnection() throws SQLException {
        // Close the database connection
        if (conn != null) {
            conn.close();
        }
    }
}

