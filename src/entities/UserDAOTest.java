package entities;

import java.sql.*;

public class UserDAOTest {
    private Connection conn;
    private UserDataAccess userDao;

    public UserDAOTest() throws SQLException {
        String conString = "jdbc:postgresql://localhost:5432/AMS";
        conn = DriverManager.getConnection(conString, "postgres", "qwertyzsdv");
        userDao = new UserDataAccess(conn);
    }

    public void testSaveAndFind() throws SQLException {
        User user = new User(0, "Mendeke", "GDEti", 1, 100.0);

        userDao.save(user);
        System.out.println("Saved user: " + user);

        User retrievedUser = userDao.find(user.getId());
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

