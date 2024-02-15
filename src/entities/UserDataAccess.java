package entities;

import java.sql.*;

public class UserDataAccess {
    private Connection conn;

    public UserDataAccess(Connection conn) {
        this.conn = conn;
    }

    public User find(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new User(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getInt("groupNumber"), rs.getDouble("attendance"));
        } else {
            return null;
        }
    }







