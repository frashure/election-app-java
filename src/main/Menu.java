package main;
import util.CandidateDAO;
import util.ElectionCandidates;
import util.ElectionDAO;

import java.sql.SQLException;
import java.util.Scanner;

public final class Menu {

    private static Scanner in = new Scanner(System.in);

    public static int printMenu() {
        int option;
        System.out.println("\nVIRGINIA ELECTIONS API MANAGEMENT CONSOLE\n");
        System.out.println("****Menu****");
        System.out.println("1.  Search for a candidate");
        System.out.println("2.  Create a new candidate");
        System.out.println("3.  Search for an election");
        System.out.println("4.  Create a new election");
        System.out.print("\nSelect an option (or 0 to exit): ");
        option = in.nextInt();

        return option;
    }

    public static int searchCandidates() throws SQLException {
        System.out.print("Enter candidate first name: ");
        String fName = in.next();
        System.out.print("Enter candidate last name: ");
        String lName = in.next();

        boolean exists = CandidateDAO.doesExist(fName, lName);

        if (exists) {
            int id = CandidateDAO.getId(fName, lName);
            System.out.println("\nCandidate id: " + id);
            return id;
        }
        else if (!exists) {
            return 0;
        }
        return 0;
    }

    public static int createCandidate() throws SQLException {
        System.out.print("Candidate first name: ");
        String fName = in.next();
        System.out.print("Candidate last name: ");
        String lName = in.next();
        System.out.print("Candidate party: ");
        String party = in.next();
        System.out.print("Candidate website: ");
        String website = in.next();

        System.out.println("NEW CANDIDATE:");
        System.out.println("Name: " + fName + " " + lName);
        System.out.println("Party: " + party);
        System.out.println("Website: " + website);
        System.out.println("\nIs this information correct? (y/n): ");
        String confirm = in.next();

        if (confirm.charAt(0) == 'y') {
            CandidateDAO.insert(fName, lName, party, website);
            int id = CandidateDAO.getLastInsertId();
            return id;
        }
        else {
            return 0;
        }
    }

    public static int searchElections() throws SQLException {
        int year;
        String office;
        int district = 0;
        String type;
        String party = null;

        System.out.print("Election year: ");
        year = in.nextInt();
        System.out.print("Office: ");
        office = in.next();
        if (!office.equalsIgnoreCase("president") || !office.equalsIgnoreCase("ussenate")) {
            System.out.print("District: ");
            district = in.nextInt();
        }
        System.out.print("Type: ");
        type = in.next();
        if (type.equalsIgnoreCase("primary")) {
            System.out.print("Party: ");
            party = in.next();
        }

        boolean check = ElectionDAO.doesExist(type, office, party, district, year);
        if (check) {
            int eid = ElectionDAO.getId(year, office, district, type, party);
            return eid;
        }
        else {
            System.out.println("\nElection not found");
            return 0;
        }
    }

    public static int createElection() throws SQLException {
        System.out.println("Enter date formatted as yyyy-mm-dd :");
        String date = in.next();
        System.out.println("Office ID: ");
        String office = in.next();
        System.out.println("District (enter 0 for null): ");
        int district = in.nextInt();
        System.out.println("Type (general, primary, special): ");
        String type = in.next();
        String party = null;
        if (type.equals("primary")) {
            System.out.println("Party: ");
            party = in.next();
        }

        ElectionDAO.insert(date, office, district, type, party);
        int eid = ElectionDAO.getLastInsertId();
        return eid;

    }

    public static boolean insertCandidateIntoElection(int candidateId) throws SQLException {
        int electionId = searchElections();

        if (electionId != 0) {
            System.out.println("Election ID: " + electionId);
            boolean ec_inserted = ElectionCandidates.insert(candidateId, electionId);
            if (ec_inserted) {
                System.out.println("Candidate inserted!");
                return true;
            }
            else {
                System.out.println("Candidate insert failed.");
                return false;
            }
        }
        else if (electionId == 0) {
            System.out.print("Election not found. Create election? (y/n): ");

            if (in.next().charAt(0) == 'y') {
                electionId = Menu.createElection();
                ElectionCandidates.insert(candidateId, electionId);
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

}
