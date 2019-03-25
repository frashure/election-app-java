package util;
import main.Candidate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CandidateDAO implements DataAccessObject<Candidate> {

    @Override
    public Candidate getById(int id) {
        String sql = "SELECT * FROM candidates WHERE candidate_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();



         rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();

        }
    }

    @Override
    public boolean insert(String fName, String mName, String lName, String party, String website) {
        String sql = "INSERT INTO candidates (first_name, middle_name, last_name, party_id, website VALUES (?, ?, ? ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fName);
            stmt.setString(2, mName);
            stmt.setString(3, lName);
            stmt.setString(4, party);
            stmt.setString(5, website);
            stmt.executeQuery();

            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
