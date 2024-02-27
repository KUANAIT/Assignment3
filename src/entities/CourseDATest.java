package entities;

import services.CourseService;

import java.sql.*;
import java.util.Scanner;

public class CourseDATest {
    private Connection conn;
    private CourseService courseService;

    public CourseDATest() throws SQLException {
        String conString = "jdbc:postgresql://localhost:5432/AMS";
        conn = DriverManager.getConnection(conString, "postgres", "qwertyzsdv");
        courseService = new CourseService(conn);
    }

    public void testSaveAndFind() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter course details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();

        Course course = new Course(0, name, description);

        courseService.save(course);
        System.out.println("Saved course: " + course);

        Course retrievedCourse = courseService.find(course.getId());
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

    public void testUpdate() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter course ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new course details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();

        Course course = new Course(id, name, description);

        courseService.update(course);
        System.out.println("Updated course: " + course);
    }

    public void testDelete() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter course ID to delete:");
        int id = scanner.nextInt();

        courseService.delete(id);
        System.out.println("Deleted course with ID: " + id);
    }

    public void testFind() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter course ID to find:");
        int id = scanner.nextInt();

        Course course = courseService.find(id);
        if (course != null) {
            System.out.println("Found course: " + course);
        } else {
            System.out.println("No course found with ID: " + id);
        }
    }
    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}

