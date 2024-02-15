package entities;

import java.sql.*;
import java.time.LocalDate;

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
        // Create an attendance record
        AttendanceRecord record = new AttendanceRecord(0, 5, 1, LocalDate.now(), true);

        // Save the attendance record
        attendanceRecordDao.save(record);
        System.out.println("Saved attendance record: " + record);

        // Retrieve the attendance record
        AttendanceRecord retrievedRecord = attendanceRecordDao.find(record.getId());
        System.out.println("Retrieved attendance record: " + retrievedRecord);

        // Check that the retrieved attendance record is not null and has the expected properties
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
    }

    public void closeConnection() throws SQLException {
        // Close the database connection
        if (conn != null) {
            conn.close();
        }
    }
}
