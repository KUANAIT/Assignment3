import entities.User;

import java.sql.*;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String conString = "jdbc:postgresql://localhost:5432/AMS";
        Connection con = null;
        ResultSet rs = null;
        Statement statement = null;
        ArrayList<User> users = new ArrayList<User>();
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
