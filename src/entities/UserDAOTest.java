package entities;

import services.UserService;

import java.sql.*;
import java.util.Scanner;


public class UserDAOTest {
    private Connection conn;
    private UserService userService;

    public UserDAOTest() throws SQLException {
        String conString = "jdbc:postgresql://localhost:5432/AMS";
        conn = DriverManager.getConnection(conString, "postgres", "qwertyzsdv");
        userService = new UserService(conn);
    }

    public void testSaveAndFind() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter user details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Surname: ");
        String surname = scanner.nextLine();
        System.out.print("Group Number: ");
        int groupNumber = scanner.nextInt();
        System.out.print("Attendance: ");
        double attendance = scanner.nextDouble();

        User user = new User(0, name, surname, groupNumber, attendance);

        userService.save(user);
        System.out.println("Saved user: " + user);

        User retrievedUser = userService.find(user.getId());
        System.out.println("Retrieved user: " + retrievedUser);

        if (retrievedUser != null) {
            System.out.println("Retrieved user is not null");
            if (user.getName().equals(retrievedUser.getName()) &&
                    user.getSurname().equals(retrievedUser.getSurname()) &&
                    user.getGroupNumber() == retrievedUser.getGroupNumber() &&
                    Math.abs(user.getAttendance() - retrievedUser.getAttendance()) < 0.01) {
                System.out.println("Retrieved user has the expected properties");
            } else {
                System.out.println("Retrieved user does not have the expected properties");
            }
        } else {
            System.out.println("Retrieved user is null");
        }
    }
    public void testUpdate() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter user ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new user details:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Surname: ");
        String surname = scanner.nextLine();
        System.out.print("Group Number: ");
        int groupNumber = scanner.nextInt();
        System.out.print("Attendance: ");
        double attendance = scanner.nextDouble();

        User user = new User(id, name, surname, groupNumber, attendance);

        userService.update(user);
        System.out.println("Updated user: " + user);
    }

    public void testDelete() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter user ID to delete:");
        int id = scanner.nextInt();

        userService.delete(id);
        System.out.println("Deleted user with ID: " + id);
    }

    public void testFind() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter user ID to find:");
        int id = scanner.nextInt();

        User user = userService.find(id);
        if (user != null) {
            System.out.println("Found user: " + user);
        } else {
            System.out.println("No user found with ID: " + id);
        }
    }

     public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
