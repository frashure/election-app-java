import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class Election {

    private static int nextId = 1;
    private final int id = setId();
    private final String type;
    private final GregorianCalendar date;
    private Office office;
    private ArrayList<Candidate> candidates = new ArrayList<>();

    private static final String USERNAME = System.getenv("DB_USER");
    private static final String PASS = System.getenv("DB_PASS");
    private static final String CONN_STRING = System.getenv("DB_URL");


    public Election(GregorianCalendar date, Office office, String type) {
        this.date = date;
        this.office = office;
        this.type = type;
    }

    private int setId() {
        int i = nextId;
        nextId++;
        return i;
    }


    public GregorianCalendar getDate() {
        return this.date;
    }

    public Office getOffice() {
        return this.office;
    }

    public int getId() {
        return this.id;
    }

    public void addCandidate(Candidate c) {
        this.candidates.add(c);
    }

    public ArrayList getCandidates() {
        return this.candidates;
    }

    public String getType() {
        return this.type;
    }

    public static GregorianCalendar dateConverter(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = date;
        Date newDate = sdf.parse(dateString);
        Calendar c = Calendar.getInstance();
        c.setTime(newDate);

        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d =  c.get(Calendar.DAY_OF_WEEK);

        GregorianCalendar gc = new GregorianCalendar(y, m, d);
        return gc;
    }

    public static ArrayList<Election> getElections() throws SQLException, ParseException {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        ArrayList<Candidate> candidates = new ArrayList<>();
        ArrayList<Election> elections = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASS);
            System.out.println("Connected");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery("SELECT e.*, c.*, ec.election_id AS candidate_election, p.party_name, er.num_votes, er.winner " +
                    "FROM elections e " +
                    "LEFT JOIN election_candidates ec " +
                    "ON e.election_id = ec.election_id " +
                    "LEFT JOIN candidates c " +
                    "ON ec.candidate_id = c.candidate_id " +
                    "LEFT JOIN parties p " +
                    "ON c.party_id = p.party_id " +
                    "LEFT JOIN election_results er " +
                    "ON c.candidate_id = er.candidate_id " +
                    "WHERE year(date) = 2019 " +
                    "ORDER BY e.election_id;");

            while (rs.next()) {
                String eid = rs.getString("election_id");
                GregorianCalendar date = dateConverter(rs.getString("date"));
                Office o = new Office(rs.getString("office_id"));
                int district = Integer.parseInt(rs.getString("district"));
                String t = rs.getString("type");

                if (t.equalsIgnoreCase("primary")) {
                    Party p = new Party(rs.getString("party_id"));
                    PrimaryElection pe = new PrimaryElection(date, o, p, district, t);
                    elections.add(pe);
                } else {
                    GeneralElection ge = new GeneralElection(date, o, t);
                    elections.add(ge);
                }
//                int id = Integer.parseInt(rs.getString("candidate_id"));
//                String fName = rs.getString("first_name");
//                String lName = rs.getString("last_name");
//                String partyName = rs.getString("party_id");
//                Party p = new Party(null, partyName, null);
//
//                Candidate c = new Candidate(fName, lName, p, null);
//                candidates.add(c);
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

//        for (Candidate c : candidates) {
//            System.out.println("Name: " + c.getName());
//            System.out.println("Party: " + c.getParty() + "\n");
//        }

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
        return elections;
    }

//    public ArrayList<Election> buildElections() {
//        // get elections from DB
//        // iterate through results and create instances
//        // add to array
//    }

}
