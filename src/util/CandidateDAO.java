package util;

import java.sql.*;

@SuppressWarnings({"unused", "Duplicates"})
public final class CandidateDAO {

    public static int getLastInsertId(Connection conn) throws SQLException {
        String sql = "SELECT LAST_INSERT_ID()";

        try (Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            int id = rs.getInt("LAST_INSERT_ID()");

            rs.close();
            return id;

        } catch (SQLException e) {
            throw new SQLException("Error querying database", e);
        }
    }

    public static int insert(String fName, String lName, String party, String website) {
        String sql = "INSERT INTO candidates (first_name, last_name, party_id, website) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, party);
            stmt.setString(4, website);
            stmt.execute();

            int id = getLastInsertId(conn);

            return id;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static boolean update() {
        return false;
    }

    public static boolean delete() {
        return false;
    }

    public static boolean doesExist(String fName, String lName) throws SQLException {
        String sql = "SELECT * FROM candidates WHERE first_name = ? AND last_name = ?";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fName);
            stmt.setString(2, lName);
            ResultSet rs = stmt.executeQuery();

            // if rs.last() is false, no candidates were found
            if (!rs.last()) {
                rs.close();
                return false;
            }
            else {
                rs.close();
                return true;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public static int getId(String fName, String lName) throws SQLException {

        String sql = "SELECT * FROM candidates WHERE first_name = ? AND last_name = ?";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fName);
            stmt.setString(2, lName);
            ResultSet rs = stmt.executeQuery();

            rs.next();
            int id = rs.getInt("candidate_id");

            rs.close();
            return id;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }



}
