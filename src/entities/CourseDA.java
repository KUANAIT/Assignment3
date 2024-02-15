package entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseDA {
    private Connection conn;

    public CourseDA(Connection conn) {
        this.conn = conn;
    }

    public Course find(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM courses WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new Course(rs.getInt("id"), rs.getString("name"), rs.getString("description"));
        } else {
            return null;
        }
    }
    public void save(Course course) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO courses (name, description) VALUES (?, ?)");
        stmt.setString(1, course.getName());
        stmt.setString(2, course.getDescription());
        stmt.executeUpdate();
    }

    public void update(Course course) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE courses SET name = ?, description = ? WHERE id = ?");
        stmt.setString(1, course.getName());
        stmt.setString(2, course.getDescription());
        stmt.setInt(3, course.getId());
        stmt.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM courses WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }

}

