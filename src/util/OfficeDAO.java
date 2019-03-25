package util;
import main.Office;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OfficeDAO extends DataAccessObject<Office> {

    @Override
    public Office getById(String id) {
        String sql = "SELECT * FROM offices WHERE office_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            rs.next();


        }
    }

    @Override
    public boolean insert() {
        return false;
    }

    @Override
    public boolean update() {
        return false;
    }

    @Override
    public boolean delete() {
        return false;
    }
}
