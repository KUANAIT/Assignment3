package controllers;

import entities.AttendanceRecord;
import services.AttendanceRecordService;
import services.UserService;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.InputMismatchException;


public class AttendanceRecordController {
    private Connection conn;
    private AttendanceRecordService attendanceRecordService;
    private UserService userService;

    public AttendanceRecordController() throws SQLException {
        String conString = "jdbc:postgresql://localhost:5432/AMS";
        conn = DriverManager.getConnection(conString, "postgres", "qwertyzsdv");
        attendanceRecordService = new AttendanceRecordService(conn);
        userService = new UserService(conn);
    }

    public void SaveAndFind() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter attendance record details:");
        System.out.print("User ID: ");
        int userId = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                userId = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("That's not a valid input. Please enter an integer.");
                scanner.next();
            }
        }
        System.out.print("Course ID: ");
        int courseId = scanner.nextInt();
        System.out.print("Is present (true/false): ");
        boolean isPresent = scanner.nextBoolean();

        AttendanceRecord record = new AttendanceRecord(0, userId, courseId, LocalDate.now(), isPresent);

        attendanceRecordService.save(record);
        System.out.println("Saved attendance record: " + record);

        if (isPresent) {
            userService.addAttendance(userId, 1.3);
        }

        AttendanceRecord retrievedRecord = attendanceRecordService.find(record.getId());
        System.out.println("Retrieved attendance record: " + retrievedRecord);

        if (retrievedRecord != null) {
            System.out.println("Retrieved attendance record is not null");
            if (record.getUserId() == retrievedRecord.getUserId() &&
                    record.getCourseId() == retrievedRecord.getCourseId() &&
                    record.getDate().equals(retrievedRecord.getDate()) &&
                    record.isPresent() == retrievedRecord.isPresent()) {
                System.out.println("Retrieved attendance record has the expected properties");
            } else {
                System.out.println("Retrieved attendance record does not have the expected properties");
            }
        } else {
            System.out.println("Retrieved attendance record is null");
        }

        scanner.close();
    }

    public void Update() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter attendance record ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new attendance record details:");
        System.out.print("User ID: ");
        int userId = scanner.nextInt();
        System.out.print("Course ID: ");
        int courseId = scanner.nextInt();
        System.out.print("Is present (true/false): ");
        boolean isPresent = scanner.nextBoolean();

        AttendanceRecord record = new AttendanceRecord(id, userId, courseId, LocalDate.now(), isPresent);

        attendanceRecordService.update(record);
        System.out.println("Updated attendance record: " + record);
    }

    public void Delete() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter attendance record ID to delete:");
        int id = scanner.nextInt();

        attendanceRecordService.delete(id);
        System.out.println("Deleted attendance record with ID: " + id);
    }

    public void Find() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter attendance record ID to find:");
        int id = scanner.nextInt();

        AttendanceRecord record = attendanceRecordService.find(id);
        if (record != null) {
            System.out.println("Found attendance record: " + record);
        } else {
            System.out.println("No attendance record found with ID: " + id);
        }
    }
    public void closeConnection() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
