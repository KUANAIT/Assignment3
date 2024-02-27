import entities.AttendanceRecordDATest;
import entities.CourseDATest;
import entities.User;
import entities.UserDAOTest;
import services.TableOperationsService;

import java.sql.*;
import java.util.Scanner;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        String conString = "2";
        Connection con = null;
        ResultSet rs = null;
        Statement statement = null;
        ArrayList<User> users = new ArrayList<User>();
        TableOperationsService.chooseTable();



        try {
            con = DriverManager.getConnection(conString, "postgres", "qwertyzsdv");
            statement = con.createStatement();
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


