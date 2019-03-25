package util;

import main.Party;

import java.sql.*;
import java.util.ArrayList;

@SuppressWarnings({"Duplicates", "unused"})
public final class PartyDAO {

    private static ArrayList<Party> parties = new ArrayList<>();

    public static Party getById(String id) throws SQLException {
        String sql = "SELECT * FROM parties WHERE party_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            rs.next();

            String pid = rs.getString("party_id");
            String name = rs.getString("party_name");
            String website = rs.getString("website");

            Party p = new Party(pid, name, website);

            rs.close();
            return p;

        } catch (SQLException e) {
            throw e;
        }
    }

    public static boolean insert(String partyId, String partyName, String website) {
        String sql = "INSERT INTO parties (party_id, party_name, website) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, partyId);
            stmt.setString(2, partyName);
            stmt.setString(3, website);

            stmt.execute();

            return true;
        } catch (SQLException e) {
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

    public static ArrayList<Party> getParties() throws SQLException {
        String sql = "SELECT * FROM parties";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String pid = rs.getString("party_id");
                String name = rs.getString("party_name");
                String website = rs.getString("website");

                Party p = new Party(pid, name, website);

                parties.add(p);
            }

            ArrayList<Party> newList = new ArrayList<>(parties);

            rs.close();
            return newList;

        } catch (SQLException e) {
            throw e;
        }
    }
}
