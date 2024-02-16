package entities;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class AttendanceRecordDATest {
    private Connection conn;
    private AttendanceRecordDA attendanceRecordDao;

    public AttendanceRecordDATest() throws SQLException {
        // Set up the database connection and the DAO
        String conString = "jdbc:postgresql://localhost:5432/AMS";
        conn = DriverManager.getConnection(conString, "postgres", "qwertyzsdv");
        attendanceRecordDao = new AttendanceRecordDA(conn);
    }

    public void testSaveAndFind() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter attendance record details:");
        System.out.print("User ID: ");
        int userId = scanner.nextInt();
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

    public void closeConnection() throws SQLException {
        // Close the database connection
        if (conn != null) {
            conn.close();
        }
    }
}
