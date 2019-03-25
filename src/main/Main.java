package main;

import util.CandidateDAO;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws SQLException, ParseException {


//        ArrayList<Election> elections = Election.getElections();
//
//        for (Election e : elections) {
//            System.out.println("Office: " + e.getOffice().getName());
//            System.out.println("Type: " + e.getType());
//            System.out.println("Primary?: " + (e.getType().equalsIgnoreCase("primary")));
//            if (e.getType().equalsIgnoreCase("primary")) {
//                System.out.println("Party: " + ((PrimaryElection) e).getParty().getShortName());
//            }
//            System.out.println("ID: " + e.getId());
//            Date dateToFormat = e.getDate().getTime();
//            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
//            String formattedDate = sdf.format(dateToFormat);
//            System.out.println("Date: " + formattedDate + "\n");
//        }



        Scanner in = new Scanner(System.in);
        int option = 1;

        do {
            System.out.println("\nVIRGINIA ELECTIONS API MANAGEMENT CONSOLE\n\n");
            System.out.println("****Menu****");
            System.out.println("1.  Check for a candidate");
            System.out.println("2.  Create a new candidate");
            System.out.print("\nSelect an option (or 0 to exit): ");
            option = in.nextInt();

            if (option == 1) {
                System.out.print("Enter candidate first name: ");
                String fName = in.next();
                System.out.print("Enter candidate last name: ");
                String lName = in.next();

                boolean exists = CandidateDAO.doesExist(fName, lName);

                if (exists) {
                    int id = CandidateDAO.getId(fName, lName);
                    System.out.println("Candidate id: " + id);
                }
                else if (!exists) {
                    System.out.println("Candidate does not exist in database");
                }

            }
            else if (option == 2) {
                System.out.println("Coming soon");
            }

        }
        while (option != 0);




    }
}
