package dao;

import db.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Visitor;

public class VisitorDAO {

    // INSERT VISITOR
    public boolean addVisitor(Visitor v) {
        String sql = "INSERT INTO visitors " +
                     "(name, phone, purpose, person_to_meet, visit_date, visit_time) " +
                     "VALUES (?, ?, ?, ?, CURDATE(), CURTIME())";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getName());
            ps.setString(2, v.getPhone());
            ps.setString(3, v.getPurpose());
            ps.setString(4, v.getPersonToMeet());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // FETCH ALL VISITORS
    public List<Visitor> getAllVisitors() {
        List<Visitor> list = new ArrayList<>();
        String sql = "SELECT * FROM visitors ORDER BY id DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Visitor v = new Visitor(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("phone"),
                    rs.getString("purpose"),
                    rs.getString("person_to_meet"),
                    rs.getDate("visit_date").toLocalDate(),
                    rs.getTime("visit_time").toLocalTime()
                );
                list.add(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}