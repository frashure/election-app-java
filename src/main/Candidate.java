package main;

import java.util.ArrayList;
import java.util.List;

public class Candidate {

    private final int id;
    private final String firstName;
    private final String lastName;
//    private final Party party;
    private final String party;
    private final String website;
    private List<Election> elections = new ArrayList<>();


    public Candidate(int id, String firstName, String lastName, String party, String website) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.party = party;
        this.website = website;
    }

    public int getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getFullName() {
        String name = firstName + " " + lastName;
        return name;
    }

    public String getParty() {
        return this.party;
    }

//    public void setElections(List elections) {
//        this.elections = elections.clone();
//    }

    public void addElection(Election e) {
        this.elections.add(e);
    }

    public List getElections() {
        return this.elections;
    }

    public void printInfo() {
        System.out.println("Name: " + this.firstName + " " + this.lastName);
        System.out.println("Party: " + this.party);
        if (this.elections.size() != 0) {
            System.out.println("Elections: ");
            for (Election e:this.elections) {
                System.out.println("\tOffice: " + e.getOffice());
                System.out.println("\tDistrict: " + e.getDistrict());
                System.out.println("\tType: " + e.getType());
            }
        }
        else {
            System.out.println("Not associated with any election.");
        }
    }

}
