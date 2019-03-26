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
        int option;

        do {
            option = Menu.printMenu();

            if (option == 1) {
                Menu.searchCandidates();
            }
            else if (option == 2) {
                System.out.print("Coming soon");
            }
            else if (option == 3) {
                Menu.searchElections();
            }
            else  if (option != 0) {
                System.out.print("Please enter a valid number: ");
                option = in.nextInt();
            }

        }
        while (option != 0);




    }
}
