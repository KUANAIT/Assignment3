package org.example.services;

import org.example.entities.AttendanceRecord;

import java.sql.*;
import java.sql.Date;

public class AttendanceRecordService {
    private Connection conn;

    public AttendanceRecordService(Connection conn) {
        this.conn = conn;
    }

    public AttendanceRecord find(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM attendancerecord WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return new AttendanceRecord(rs.getInt("id"), rs.getInt("userId"), rs.getInt("courseId"), rs.getDate("date").toLocalDate(), rs.getBoolean("present"));
        } else {
            return null;
        }
    }

    public void save(AttendanceRecord record) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO attendancerecord (userId, courseId, date, present) VALUES (?, ?, ?, ?)");
        stmt.setInt(1, record.getUserId());
        stmt.setInt(2, record.getCourseId());
        stmt.setDate(3, Date.valueOf(record.getDate()));
        stmt.setBoolean(4, record.isPresent());
        stmt.executeUpdate();
    }

    public void update(AttendanceRecord record) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE attendancerecord SET userId = ?, courseId = ?, date = ?, present = ? WHERE id = ?");
        stmt.setInt(1, record.getUserId());
        stmt.setInt(2, record.getCourseId());
        stmt.setDate(3, Date.valueOf(record.getDate()));
        stmt.setBoolean(4, record.isPresent());
        stmt.setInt(5, record.getId());
        stmt.executeUpdate();
    }

    public void delete(int id) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM attendancerecord WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}

