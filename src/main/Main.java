package main;

import util.ElectionCandidates;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException{

        Scanner in = new Scanner(System.in);
        int option;

        do {
            option = Menu.printMenu();

            if (option == 1) {
                int candidateId = Menu.searchCandidates();
                if (candidateId == 0) {
                    System.out.print("Candidate not found. Create candidate? (y/n): ");
                    char res = in.next().charAt(0);
                    if (res == 'y') {
                        candidateId = Menu.createCandidate();
                    }
                    else if (res == 'n') {
                        continue;
                    }
                }
                // this seems to be always true; check that select last_insert_id() is working properly
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
            else if (option == 2) {
                int candidateId = Menu.createCandidate();
                System.out.print("Would you like to add this candidate to an election? (y/n): ");
                String res = in.next();
                if (res.charAt(0) == 'y') {
                    Menu.insertCandidateIntoElection(candidateId);
                }

            }
            else if (option == 3) {
                Menu.searchElections();
            }
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
