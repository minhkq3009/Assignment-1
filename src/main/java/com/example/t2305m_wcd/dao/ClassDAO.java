package com.example.t2305m_wcd.dao;

import com.example.t2305m_wcd.database.Database;
import com.example.t2305m_wcd.entity.Class;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClassDAO implements DAOInterface<Class, Long> {
    @Override
    public List<Class> all() {
        List<Class> list = new ArrayList<>();
        try {
            String sql = "SELECT * FROM classes";
            Database db = Database.createInstance();
            Statement st = db.getStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Class(
                        rs.getLong("id"),
                        rs.getString("name")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void create(Class c) {
        try {
            String sql = "INSERT INTO classes(name) VALUES(?)";
            Database db = Database.createInstance();
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setString(1, c.getName());
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Class c) {
        try {
            String sql = "UPDATE classes SET name = ? WHERE id = ?";
            Database db = Database.createInstance();
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setString(1, c.getName());
            ps.setLong(2, c.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            String sql = "DELETE FROM classes WHERE id = ?";
            Database db = Database.createInstance();
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setLong(1, id);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Class find(Long id) {
        Class c = null;
        try {
            String sql = "SELECT * FROM classes WHERE id = ?";
            Database db = Database.createInstance();
            PreparedStatement ps = db.getPreparedStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                c = new Class(
                        rs.getLong("id"),
                        rs.getString("name")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
}