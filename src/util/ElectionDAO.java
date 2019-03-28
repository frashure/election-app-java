package util;
import main.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import static main.Election.dateConverter;

@SuppressWarnings({"unused", "Duplicates"})
public class ElectionDAO {

    public static int getLastInsertId() throws SQLException {
        String sql = "SELECT LAST_INSERT_ID()";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            int id = rs.getInt("LAST_INSERT_ID()");

            rs.close();
            return id;

        } catch (SQLException e) {
            throw new SQLException("Error querying database", e);
        }
    }

    public static int getId(int year, String office, int district, String type, String party) throws SQLException {

        String sql;

        if (district == 0 && party != null) {
            sql = "SELECT * FROM elections WHERE year(date) = ? AND office_id = ? AND `type` = ? AND district IS NULL AND party_id = ?";
        }
        else if (district != 0 && party == null) {
            sql = "SELECT * FROM elections WHERE year(date) = ? AND office_id = ? AND `type` = ? AND district = ?  AND party_id IS NULL";
        }
        else if (district == 0 && party == null) {
            sql = "SELECT * FROM elections WHERE year(date) = ? AND office_id = ? AND `type` = ? AND district IS NULL AND party_id IS NULL";
        }
        else {
            sql = "SELECT * FROM elections WHERE year(date) = ? AND office_id = ? AND `type` = ? AND district = ?  AND party_id = ?";
        }

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, year);
            stmt.setString(2, office);
            stmt.setString(3, type);
            if (district == 0 && party != null) {
                stmt.setString(4, party);
            }
            else if (district != 0 && party == null) {
                stmt.setInt(4, district);
            }
            else {
                stmt.setInt(4, district);
                stmt.setString(5, party);
            }

            ResultSet rs = stmt.executeQuery();

            rs.next();
            int id = rs.getInt("election_id");
            rs.close();

            return id;

        } catch (SQLException e) {
            throw e;
        }

    }

    public static boolean insert(String date, String office, int district, String type, String party) throws SQLException {
        String sql;

        if (district == 0 && party != null) {
            sql = "INSERT INTO elections (date, office_id, type, party_id) VALUES (?, ?, ?, ?)";
        }
        else if (district != 0 && party == null) {
            sql = "INSERT INTO elections (date, office_id, type, district) VALUES (?, ?, ?, ?)";
        }
        else if (district == 0 && party == null) {
            sql = "INSERT INTO elections (date, office_id, type) VALUES (?, ?, ?)";
        }
        else {
            sql = "INSERT INTO elections (date, office_id, type, district, party_id) VALUES (?, ?, ?, ?, ?)";
        }

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, date);
            stmt.setString(2, office);
            stmt.setString(3, type);
            if (district == 0 && party != null) {
                stmt.setString(4, party);
            }
            else if (district != 0 && party == null) {
                stmt.setInt(4, district);
            }
            else if (district != 0 && party != null){
                stmt.setInt(4, district);
                stmt.setString(5, party);
            }

            stmt.execute();
            return true;

        } catch (SQLException e) {
            throw e;
        }
    }

    public boolean update() {
        return false;
    }

    public boolean delete() {
        return false;
    }

    public static boolean doesExist(String type, String office, String party, int district, int year) throws SQLException {
        String sql;

        if (district == 0 && party != null) {
            sql = "SELECT * FROM elections WHERE year(date) = ? AND office_id = ? AND `type` = ? AND district IS NULL AND party_id = ?";
        }
        else if (district != 0 && party == null) {
            sql = "SELECT * FROM elections WHERE year(date) = ? AND office_id = ? AND `type` = ? AND district = ?  AND party_id IS NULL";
        }
        else if (district == 0 && party == null) {
            sql = "SELECT * FROM elections WHERE year(date) = ? AND office_id = ? AND `type` = ? AND district IS NULL AND party_id IS NULL";
        }
        else {
            sql = "SELECT * FROM elections WHERE year(date) = ? AND office_id = ? AND `type` = ? AND district = ?  AND party_id = ?";
        }

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, year);
            stmt.setString(2, office);
            stmt.setString(3, type);
            if (district == 0 && party != null) {
                stmt.setString(4, party);
            }
            else if (district != 0 && party == null) {
                stmt.setInt(4, district);
            }
            else {
                stmt.setInt(4, district);
                stmt.setString(5, party);
            }

            ResultSet rs = stmt.executeQuery();

            if (!rs.last()) {
                rs.close();
                return false;
            }
            else {
                rs.close();
                return true;
            }
        } catch (SQLException e) {
            throw e;
        }
    }
}
