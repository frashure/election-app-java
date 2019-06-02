package util;

import main.Candidate;
import main.Election;
import main.GeneralElection;
import main.PrimaryElection;

import java.sql.*;
import java.text.ParseException;

@SuppressWarnings({"unused", "Duplicates"})
public final class CandidateDAO {

    // method is superfluous; this is accomplished by setting Statement object to return generated keys
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
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, party);
            stmt.setString(4, website);
            stmt.executeUpdate();

            ResultSet keys = stmt.getGeneratedKeys();
            keys.next();
            int id = keys.getInt(1);
            keys.close();

            return id;
        } catch (SQLException e) {
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
            } else {
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

    public static Candidate getInfo(int id) throws SQLException, ParseException {

        String sql = "SELECT c.*, e.* FROM candidates c LEFT JOIN election_candidates ec ON c.candidate_id = ec.candidate_id LEFT JOIN elections e ON ec.election_id = e.election_id WHERE c.candidate_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Create candidate
                int cid = rs.getInt("candidate_id");
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                String party = rs.getString("party_id");

                Candidate c = new Candidate(cid, fName, lName, party, null);

                // If candidate is running in an election, election_id will not be 0; create election, move to next
                    while (rs.getInt("election_id") != 0) {
                        int eid = rs.getInt("election_id");
                        String dateString = rs.getString("date");
                        String type = rs.getString("type");
                        String office = rs.getString("office_id");
                        int district = rs.getInt("district");

                        if (type.equalsIgnoreCase("primary")) {
                            String eParty = rs.getString("party_id");
                            int primaryFor = rs.getInt("primary_for");
                            Election e = new PrimaryElection(eid, Election.dateConverter(dateString), office, party, district, type);
                            ((PrimaryElection) e).setPrimaryFor(primaryFor);
                            c.addElection(e);
                        }
                        else {
                            Election e = new GeneralElection(eid, Election.dateConverter(dateString), office, type, district);
                            c.addElection(e);
                        }
                        if (!rs.next()) {
                            break;
                    }
                }


                rs.close();
                return c;
            }

            else {
                rs.close();
                return null;
            }

        } // end try

        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
