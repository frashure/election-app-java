package util;
import main.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import static main.Election.dateConverter;

@SuppressWarnings({"unused", "Duplicates"})
public class ElectionDAO {

    public static int getId(int year, String office, int district, String type, String party) throws SQLException {

        String sql = "SELECT * FROM elections WHERE year(date) = ? AND office_id = ? AND district = ? AND `type` = ? AND party_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, year);
            stmt.setString(2, office);
            if (district == 0) {
                stmt.setNull(3, Types.INTEGER);
            }
            else {
                stmt.setInt(3, district);
            }
            stmt.setString(4, type);
            stmt.setString(5, party);
            ResultSet rs = stmt.executeQuery();

            int id = rs.getInt("election_id");
            rs.close();

            return id;

        } catch (SQLException e) {
            throw e;
        }

    }

//    public Election getById(int id) {
//
//        String sql = "SELECT * FROM elections WHERE election_id = ?";
//        try (Connection conn = ConnectionFactory.getConnection();
//            PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setInt(1, id);
//            ResultSet rs = stmt.executeQuery();
//
//            rs.next();
//
//            if (rs.getString("type").equals("primary")) {
//                int eid = Integer.parseInt(rs.getString("election_id"));
//                String type = rs.getString("type");
//                GregorianCalendar date = dateConverter(rs.getString("date"));
//                int district = rs.getInt("district");
//                Party party;
//                Office office;
//
//
//                PrimaryElection e = new PrimaryElection();
//            }
//            else {
//                int eid = Integer.parseInt(rs.getString("election_id"));
//                String type;
//                GregorianCalendar date;
//                int district;
//                Office office;
//            }
//
//            rs.close();
//
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//            return true;
//        }
//    }

    public boolean insert(String date, String office, int district, String type, String party, int primaryFor) {
        String sql = "INSERT INTO candidates (date, office, district, type, party, primaryFor) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, date);
            stmt.setString(2, office);
            if (district == 0) {
                stmt.setNull(3, Types.INTEGER);
            }
            else {
                stmt.setInt(3, district);
            }
            stmt.setString(4, type);
            stmt.setString(5, party);
            stmt.setInt(6, primaryFor);

            stmt.execute();

            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update() {
        return false;
    }

    public boolean delete() {
        return false;
    }

//    public static ArrayList<Election> getElections() throws SQLException {
//
//        String sql = "SELECT * FROM elections";
//
//        try (Connection conn = ConnectionFactory.getConnection();
//             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//             ResultSet rs = stmt.executeQuery(sql)) {
//            while (rs.next()) {
//                int id = Integer.parseInt(rs.getString("election_id"));
//                GregorianCalendar date = dateConverter(rs.getString("date"));
//                String type = rs.getString("type");
//                Office office = rs.getString("office_id");
//
//                if (type.equals("primary")) {
//                    Party p
//                    PrimaryElection pe = new PrimaryElection(date. office, party, district, type)
//                }
//            }
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }

    public static boolean doesExist(String type, String office, String party, int district, int year) throws SQLException {

        String sql = "SELECT * FROM elections WHERE `type` = ? AND office_id = ? AND party_id = ? AND district = ? AND year(date) = ?";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, type);
            stmt.setString(2, office);
            stmt.setString(3, party);
            if (district == 0) {
                stmt.setNull(4, Types.INTEGER);
            }
            else {
                stmt.setInt(4, district);
            }
            stmt.setInt(5, year);
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
