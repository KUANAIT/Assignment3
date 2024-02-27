package services;

import entities.AttendanceRecordDATest;
import entities.CourseDATest;
import entities.UserDAOTest;

import java.sql.SQLException;
import java.util.Scanner;

public class TableOperationsService {
    public static void chooseTable() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose a table to modify:");
        System.out.println("1. User");
        System.out.println("2. Course");
        System.out.println("3. Attendance Record");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                UserDAOTest userTest = new UserDAOTest();
                chooseOperation(userTest);
                break;
            case 2:
                CourseDATest courseTest = new CourseDATest();
                chooseOperation(courseTest);
                break;
            case 3:
                AttendanceRecordDATest attendanceTest = new AttendanceRecordDATest();
                chooseOperation(attendanceTest);
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
                break;
        }

        scanner.close();
    }

    public static void chooseOperation(Object test) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an operation:");
        System.out.println("1. Save");
        System.out.println("2. Update");
        System.out.println("3. Delete");
        System.out.println("4. Find");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                if (test instanceof UserDAOTest) {
                    ((UserDAOTest) test).testSaveAndFind();
                } else if (test instanceof CourseDATest) {
                    ((CourseDATest) test).testSaveAndFind();
                } else if (test instanceof AttendanceRecordDATest) {
                    ((AttendanceRecordDATest) test).testSaveAndFind();
                }
                break;
            case 2:
                if (test instanceof UserDAOTest) {
                    ((UserDAOTest) test).testUpdate();
                } else if (test instanceof CourseDATest) {
                    ((CourseDATest) test).testUpdate();
                } else if (test instanceof AttendanceRecordDATest) {
                    ((AttendanceRecordDATest) test).testUpdate();
                }
                break;
            case 3:
                if (test instanceof UserDAOTest) {
                    ((UserDAOTest) test).testDelete();
                } else if (test instanceof CourseDATest) {
                    ((CourseDATest) test).testDelete();
                } else if (test instanceof AttendanceRecordDATest) {
                    ((AttendanceRecordDATest) test).testDelete();
                }
                break;
            case 4:
                if (test instanceof UserDAOTest) {
                    ((UserDAOTest) test).testFind();
                } else if (test instanceof CourseDATest) {
                    ((CourseDATest) test).testFind();
                } else if (test instanceof AttendanceRecordDATest) {
                    ((AttendanceRecordDATest) test).testFind();
                }
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                break;
        }
    }
}
