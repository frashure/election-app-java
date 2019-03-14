import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static final String USERNAME = System.getenv("DB_USER");
    public static final String PASS = System.getenv("DB_PASS");
    public static final String CONN_STRING = System.getenv("DB_URL");

    public static void main(String[] args) throws SQLException {


        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        ArrayList<Candidate> candidates = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASS);
            System.out.println("Connected");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("select * from candidates;");

            while (rs.next()) {
                int id = Integer.parseInt(rs.getString("candidate_id"));
                String fName = rs.getString("first_name");
                String lName = rs.getString("last_name");
                String partyName = rs.getString("party_id");
                Party p = new Party(null, partyName, null);

                Candidate c = new Candidate(fName, lName, p, null);
                candidates.add(c);
            }
        }
        catch (SQLException e) {
            System.out.println(e);
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        for (Candidate c : candidates) {
            System.out.println("Name: " + c.getName());
            System.out.println("Party: " + c.getParty() + "\n");
        }

//        ArrayList<Candidate> candidates = new ArrayList<>();
//        boolean cont = true;
//
//        while (cont) {
//            Scanner in = new Scanner(System.in);
//
//            System.out.println("CANDIDATE CREATOR:\nEnter candidate first name: ");
//            String fName = in.nextLine();
//            System.out.println("Last name: ");
//            String lName = in.nextLine();
//            System.out.println("Party: ");
//            String partyName = in.nextLine();
//
//
//            Party p = new Party("na", partyName, null);
//            Candidate c = new Candidate(fName, lName, p, null);
//
//            candidates.add(c);
//
//            System.out.println("Would you like to add more candidates? (y/n)");
//            String response = in.nextLine();
//
//            if (response.toLowerCase().charAt(0) == 'n') {
//                cont = false;
//            }
//        }
//
//        for (Candidate c : candidates) {
//            System.out.println("ID: " + c.getId());
//            System.out.println("Name: " + c.getName());
//            System.out.println("Party: " + c.getParty() + "\n");
//        }
    }
}
