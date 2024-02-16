package entities;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.InputMismatchException;


public class AttendanceRecordDATest {
    private Connection conn;
    private AttendanceRecordDA attendanceRecordDao;

    public AttendanceRecordDATest() throws SQLException {
        String conString = "jdbc:postgresql://localhost:5432/AMS";
        conn = DriverManager.getConnection(conString, "postgres", "qwertyzsdv");
        attendanceRecordDao = new AttendanceRecordDA(conn);
    }

    public void testSaveAndFind() throws SQLException {
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

        attendanceRecordDao.save(record);
        System.out.println("Saved attendance record: " + record);

        AttendanceRecord retrievedRecord = attendanceRecordDao.find(record.getId());
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

    public void testUpdate() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter attendance record ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over

        System.out.println("Enter new attendance record details:");
        System.out.print("User ID: ");
        int userId = scanner.nextInt();
        System.out.print("Course ID: ");
        int courseId = scanner.nextInt();
        System.out.print("Is present (true/false): ");
        boolean isPresent = scanner.nextBoolean();

        AttendanceRecord record = new AttendanceRecord(id, userId, courseId, LocalDate.now(), isPresent);

        attendanceRecordDao.update(record);
        System.out.println("Updated attendance record: " + record);
    }

    public void testDelete() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter attendance record ID to delete:");
        int id = scanner.nextInt();

        attendanceRecordDao.delete(id);
        System.out.println("Deleted attendance record with ID: " + id);
    }

    public void testFind() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter attendance record ID to find:");
        int id = scanner.nextInt();

        AttendanceRecord record = attendanceRecordDao.find(id);
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
