package main;

import util.ElectionCandidates;
import util.ElectionDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException, ParseException {

        Scanner in = new Scanner(System.in);
        int option;

        do {
            option = Menu.printMenu();

            // search candidates
            if (option == 1) {
                boolean found = Menu.searchCandidates();
                int candidateId = 0;
                if (!found) {
                    System.out.print("Create candidate? (y/n): ");
                    char res = in.next().charAt(0);
                    if (res == 'y') {
                        candidateId = Menu.createCandidate();
                    }
                    else if (res == 'n') {
                        continue;
                    }
                }
                if (candidateId == 0) {
                    System.out.println("Candidate not created");
                    continue;
                }
                else {
                    System.out.print("Would you like to add this candidate to an election? (y/n): ");
                    String res = in.next();
                    if (res.charAt(0) == 'y') {
                        Menu.insertCandidateIntoElection(candidateId);
                    }
                }
            }
            // create candidate
            else if (option == 2) {
                int candidateId = Menu.createCandidate();
                System.out.print("Would you like to add this candidate to an election? (y/n): ");
                String res = in.next();
                if (res.charAt(0) == 'y') {
                    Menu.insertCandidateIntoElection(candidateId);
                }

            }
            // search elections
            else if (option == 3) {
                int eid = Menu.searchElections();
                ElectionDAO.getCandidatesByElection(eid);
            }
            //
            else if (option == 4) {

            }
            else  if (option != 0) {
                System.out.print("Please enter a valid number: ");
                option = in.nextInt();
            }

        }
        while (option != 0);




    }
}
