import entities.AttendanceRecordDATest;
import entities.CourseDATest;
import entities.User;
import entities.UserDAOTest;

import java.sql.*;

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
        UserDAOTest test = new UserDAOTest();
        test.testSaveAndFind();
        CourseDATest test2 = new CourseDATest();
        test2.testSaveAndFind();
        AttendanceRecordDATest test3 = new AttendanceRecordDATest();
        test3.testSaveAndFind();


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
}
