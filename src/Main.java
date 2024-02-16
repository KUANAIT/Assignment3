import entities.AttendanceRecordDATest;
import entities.CourseDATest;
import entities.User;
import entities.UserDAOTest;

import java.sql.*;
import java.util.Scanner;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String conString = "jdbc:postgresql://localhost:5432/AMS";
        Connection con = null;
        ResultSet rs = null;
        Statement statement = null;
        ArrayList<User> users = new ArrayList<User>();
        chooseTable();



        try {
            con = DriverManager.getConnection(conString, "postgres", "qwertyzsdv");
            statement = con.createStatement();
            rs = statement.executeQuery("SELECT id, name, surname,groupnumber, attendance FROM students ORDER BY id ");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int groupNumber = rs.getInt("groupNumber");
                double attendence = rs.getDouble("attendance");
                User user = new User(id, name, surname,groupNumber, attendence);
                users.add(user);
            }
        }
        catch (SQLException e){
            System.out.println("Connection error " + e.getMessage());
        }


        finally {
            try{
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if(con != null) {
                    con.close();
                }
            }
            catch(SQLException e) {
                System.out.println("could not close connection: " + e.getMessage());
            }
        }
        for(User user : users) {
            System.out.println(user);
        }
    }
    public static void chooseTable() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a table to modify:");
        System.out.println("1. User");
        System.out.println("2. Course");
        System.out.println("3. Attendance Record");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Call the method to modify the User table
                UserDAOTest userTest = new UserDAOTest();
                userTest.testSaveAndFind();
                break;
            case 2:
                // Call the method to modify the Course table
                CourseDATest courseTest = new CourseDATest();
                courseTest.testSaveAndFind();
                break;
            case 3:
                // Call the method to modify the Attendance Record table
                AttendanceRecordDATest attendanceTest = new AttendanceRecordDATest();
                attendanceTest.testSaveAndFind();
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                break;
        }

        scanner.close();
    }
}
