package org.example.services;

import org.example.controllers.AttendanceRecordController;
import org.example.controllers.CourseController;
import org.example.controllers.UserController;

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
                UserController userTest = new UserController();
                chooseOperation(userTest);
                break;
            case 2:
                CourseController courseTest = new CourseController();
                chooseOperation(courseTest);
                break;
            case 3:
                AttendanceRecordController attendanceTest = new AttendanceRecordController();
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
                if (test instanceof UserController) {
                    ((UserController) test).SaveAndFind();
                } else if (test instanceof CourseController) {
                    ((CourseController) test).SaveAndFind();
                } else if (test instanceof AttendanceRecordController) {
                    ((AttendanceRecordController) test).SaveAndFind();
                }
                break;
            case 2:
                if (test instanceof UserController) {
                    ((UserController) test).Update();
                } else if (test instanceof CourseController) {
                    ((CourseController) test).Update();
                } else if (test instanceof AttendanceRecordController) {
                    ((AttendanceRecordController) test).Update();
                }
                break;
            case 3:
                if (test instanceof UserController) {
                    ((UserController) test).Delete();
                } else if (test instanceof CourseController) {
                    ((CourseController) test).Delete();
                } else if (test instanceof AttendanceRecordController) {
                    ((AttendanceRecordController) test).Delete();
                }
                break;
            case 4:
                if (test instanceof UserController) {
                    ((UserController) test).Find();
                } else if (test instanceof CourseController) {
                    ((CourseController) test).Find();
                } else if (test instanceof AttendanceRecordController) {
                    ((AttendanceRecordController) test).Find();
                }
                break;
            default:
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                break;
        }
    }
}
