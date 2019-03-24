package main;

public class Candidate {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final Party party;
    private final String website;


    public Candidate(int id, String firstName, String lastName, Party party, String website) {
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

    public Party getParty() {
        return this.party;
    }

}
