package util;

import main.Party;

import java.sql.*;
import java.util.ArrayList;

@SuppressWarnings("Duplicates")
public final abstract class PartyDAO implements DataAccessObject<Party> {

    private static ArrayList<Party> parties = new ArrayList<>();

    @Override
    public static Party getById(String id) throws SQLException {
        String sql = "SELECT * FROM parties WHERE party_id = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs;) {

            stmt.setString(1, id);
            rs = stmt.executeQuery();

            rs.next();

            String pid = rs.getString("party_id");
            String name = rs.getString("party_name");
            String website = rs.getString("website");

            Party p = new Party(pid, name, website);

            return p;

        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public static boolean insert(String partyId, String partyName, String website) {
        String sql = "INSERT INTO parties (party_id, party_name, website) VALUES (?, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs) {

            stmt.setString(1, partyId);
            stmt.setString(2, partyName);
            stmt.setString(3, website);

            rs = stmt.executeQuery();

            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public static boolean update() {
        return false;
    }

    @Override
    public static boolean delete() {
        return false;
    }

    public static ArrayList<Party> getParties() throws SQLException {
        String sql = "SELECT * FROM parties";
        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs) {
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                String pid = rs.getString("party_id");
                String name = rs.getString("party_name");
                String website = rs.getString("website");

                Party p = new Party(pid, name, website);

                this.parties.add(p);
            }

            ArrayList<Party> newList = new ArrayList<>(this.parties);

            return newList;

        } catch (SQLException e) {
            throw e;
        }
    }
}
