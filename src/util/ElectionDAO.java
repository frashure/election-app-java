package util;
import main.*;
import java.sql.*;
import java.util.GregorianCalendar;
import static main.Election.dateConverter;

public class ElectionDAO implements DataAccessObject<Election> {

    @Override
    public Election getById(int id) {

        String sql = "SELECT * FROM elections WHERE election_id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            rs.next();

            if (rs.getString("type").equals("primary")) {
                int eid = Integer.parseInt(rs.getString("election_id"));
                String type = rs.getString("type");
                GregorianCalendar date = dateConverter(rs.getString("date"));
                int district = rs.getInt("district");
                Party party;
                Office office;


                PrimaryElection e = new PrimaryElection();
            }
            else {
                int eid = Integer.parseInt(rs.getString("election_id"));
                String type;
                GregorianCalendar date;
                int district;
                Office office;
            }

            rs.close();

        }
        catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    @Override
    public boolean insert(String fName, String lName, String partyId, String website) {
        String sql = "INSERT INTO candidates (first_name, last_name, website, party_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, fName);
            stmt.setString(2, lName);
            stmt.setString(3, website);
            stmt.setString(4, partyId);

            stmt.execute();

            return true;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

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

    public Static ArrayList<Election> getElections() {

        String sql = "SELECT * FROM elections";

        try (Connection conn = ConnectionFactory.getConnection();
             Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("election_id"));
                GregorianCalendar date = dateConverter(rs.getString("date"));
                String type = rs.getString("type");
                Office office = rs.getString("office_id");

                if (type.equals("primary")) {
                    Party p
                    PrimaryElection pe = new PrimaryElection(date. office, party, district, type)
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
