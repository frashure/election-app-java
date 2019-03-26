package main;
import util.CandidateDAO;
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

    public static void searchCandidates() throws SQLException {
        System.out.print("Enter candidate first name: ");
        String fName = in.next();
        System.out.print("Enter candidate last name: ");
        String lName = in.next();

        boolean exists = CandidateDAO.doesExist(fName, lName);

        if (exists) {
            int id = CandidateDAO.getId(fName, lName);
            System.out.println("\nCandidate id: " + id);
        }
        else if (!exists) {
            System.out.println("\nCandidate does not exist in database");
        }
    }

    public static void searchElections() throws SQLException {
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
            System.out.println("\nElection ID: " + eid);
        }
        else {
            System.out.println("\nElection not found");
        }



    }

}
