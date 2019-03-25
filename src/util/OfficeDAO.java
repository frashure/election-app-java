package util;
import main.Office;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("unused")
public class OfficeDAO {

//    public Office getById(String id) {
//        String sql = "SELECT * FROM offices WHERE office_id = ?";
//
//        try (Connection conn = ConnectionFactory.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.setString(1, id);
//            ResultSet rs = stmt.executeQuery();
//
//            rs.next();
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public boolean insert() {
        return false;
    }

    public boolean update() {
        return false;
    }

    public boolean delete() {
        return false;
    }
}
