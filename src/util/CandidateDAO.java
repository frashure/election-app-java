package util;
import main.Candidate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("unused")
public final class CandidateDAO {

//    public static Candidate getById(int id) {
//        String sql = "SELECT * FROM candidates WHERE candidate_id = ?";
//
//        try (Connection conn = ConnectionFactory.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setInt(1, id);
//            ResultSet rs = stmt.executeQuery();
//
//
//
//         rs.close();
//         return null;
//        }
//        catch (SQLException e) {
//            e.printStackTrace();
//
//        }
//    }

    public static boolean insert(String fName, String mName, String lName, String party, String website) {
        String sql = "INSERT INTO candidates (first_name, middle_name, last_name, party_id, website VALUES (?, ?, ? ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fName);
            stmt.setString(2, mName);
            stmt.setString(3, lName);
            stmt.setString(4, party);
            stmt.setString(5, website);
            stmt.execute();

            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
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
