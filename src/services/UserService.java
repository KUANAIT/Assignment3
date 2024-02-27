package services;

import entities.User;

import java.sql.*;

public class UserService {
    private Connection conn;

    public UserService(Connection conn) {
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

    public void save(User user) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO students (name, surname, groupNumber, attendance, retake) VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getSurname());
        stmt.setInt(3, user.getGroupNumber());
        stmt.setDouble(4, user.getAttendance());
        stmt.setBoolean(5, user.isRetake());
        stmt.executeUpdate();
    }

    public void update(User user) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE students SET name = ?, surname = ?, groupNumber = ?, attendance = ?, retake = ? WHERE id = ?");
        stmt.setString(1, user.getName());
        stmt.setString(2, user.getSurname());
        stmt.setInt(3, user.getGroupNumber());
        stmt.setDouble(4, user.getAttendance());
        stmt.setBoolean(5, user.isRetake());
        stmt.setInt(6, user.getId());
        stmt.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM students WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

    public void addAttendance(int userId, double attendanceToAdd) throws SQLException {
        // Find the user
        User user = find(userId);
        if (user != null) {
            // Add to the attendance
            double newAttendance = user.getAttendance() + attendanceToAdd;
            user.setAttendance(newAttendance);

            // Update the user in the database
            update(user);
        } else {
            System.out.println("No user found with ID: " + userId);
        }
    }
}





