package com.example.t2305m_wcd.dao;

import com.example.t2305m_wcd.database.Database;
import com.example.t2305m_wcd.entity.Subject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO implements DAOInterface<Subject, Long> {

    @Override
    public List<Subject> all() {
        List<Subject> subjects = new ArrayList<>();
        try {
            String sql = "SELECT * FROM subjects";
            Database db = Database.createInstance();
            Statement st = db.getStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                subjects.add(new Subject(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("code"),
                        rs.getInt("hour")
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return subjects;
    }

    @Override
    public void create(Subject subject) {
        try {
            String sql = "INSERT INTO subjects(name, code, hour) VALUES(?, ?, ?)";
            Database db = Database.createInstance();
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setString(1, subject.getName());
            ps.setString(2, subject.getCode());
            ps.setInt(3, subject.getHour());
            ps.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(Subject subject) {
        try {
            String sql = "UPDATE subjects SET name = ?, code = ?, hour = ? WHERE id = ?";
            Database db = Database.createInstance();
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setString(1, subject.getName());
            ps.setString(2, subject.getCode());
            ps.setInt(3, subject.getHour());
            ps.setLong(4, subject.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM subjects WHERE id = ?";
            Database db = Database.createInstance();
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Subject find(Long id) {
        Subject subject = null;
        try {
            String sql = "SELECT * FROM subjects WHERE id = ?";
            Database db = Database.createInstance();
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                subject = new Subject(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("code"),
                        rs.getInt("hour")
                );
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return subject;
    }
}