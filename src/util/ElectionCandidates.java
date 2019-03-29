package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@SuppressWarnings({"unused", "Duplicates"})
public final class ElectionCandidates {

    public static boolean insert(int candidateId, int electionId) {

        String sql = "INSERT INTO election_candidates (candidate_id, election_id) VALUES (?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, candidateId);
            stmt.setInt(2, electionId);
            stmt.execute();

            return true;


        }
        catch (SQLIntegrityConstraintViolationException c) {
            System.out.println("Duplicate entry");
            return false;
        }

        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
