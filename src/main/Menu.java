package main;
import util.CandidateDAO;
import util.DateFormatter;
import util.ElectionCandidates;
import util.ElectionDAO;

import java.sql.SQLException;
import java.text.ParseException;
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
        String swallowReturn = in.nextLine();

        return option;
    }

    public static boolean searchCandidates() throws SQLException, ParseException {
        System.out.print("Enter candidate first name: ");
        String fName = in.nextLine();
        System.out.print("Enter candidate last name: ");
        String lName = in.nextLine();

        boolean exists = CandidateDAO.doesExist(fName, lName);

        if (exists) {
            int id = CandidateDAO.getId(fName, lName);
            Candidate c = CandidateDAO.getInfo(id);
            c.printInfo();
            return true;

        }
        else if (!exists) {
            System.out.print("Candidate not found. Search again? (y/n): ");
            if (in.next().charAt(0) == 'y') {
                searchCandidates();
            }
            return false;
        }
        return false;
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
        if (website.charAt(0) == 'n') {
            website = null;
        }

        System.out.println("NEW CANDIDATE:");
        System.out.println("Name: " + fName + " " + lName);
        System.out.println("Party: " + party);
        System.out.println("Website: " + website);
        System.out.println("\nIs this information correct? (y/n): ");
        String confirm = in.next();

        if (confirm.charAt(0) == 'y') {
            int id = CandidateDAO.insert(fName, lName, party, website);
            System.out.println("Candidate created!\n");
            return id;
        }
        else {
            System.out.println("Candidate not created.\n");
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
        System.out.print("Enter date formatted as yyyy-mm-dd: ");
        String date = in.next();
        System.out.print("Office ID: ");
        String office = in.next();
        System.out.print("District (enter 0 for null): ");
        int district = in.nextInt();
        System.out.print("Type (general, primary, special): ");
        String type = in.next();
        String party = null;
        int generalId = 0;
        if (type.equals("primary")) {
            System.out.print("Party: ");
            party = in.next();
            int year = DateFormatter.parseYear(date);
            generalId = ElectionDAO.getId(year, office, district, "general", null);
            if (generalId == 0) {
                System.out.println("General election not found. Create general? (y/n): ");
                if (in.next().charAt(0) == 'y') {
                    generalId = createElection();
                    if (generalId == 0) {
                        System.out.println("Failed to create general election");
                        return 0;
                    }
                    else {
                        System.out.println("General election created!");
                    }
                }
            }
        }
        System.out.println("General election ID: " + generalId);
        int id = ElectionDAO.insert(date, office, district, type, party);
        if (type.equalsIgnoreCase("primary")) {
            ElectionDAO.setPrimaryFor(id, generalId);
        }

        if (id != 0) {
            System.out.println("Election created!");
            return id;
        }
        else {
            return 0;
        }

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
                electionId = createElection();
                boolean inserted = ElectionCandidates.insert(candidateId, electionId);

                if (inserted) {
                    System.out.println("Candidate added to election!");
                    return true;
                }
                else {
                    System.out.println("Candidate insert failed.");
                    return false;
                }
            }
            else {
                return false;
            }
        }
        return false;
    }

}
