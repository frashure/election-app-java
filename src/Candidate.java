public class Candidate {

    private static int nextId = 1;
    private final int id = setId();
    private final String firstName;
    private final String lastName;
    private final String party;
    private final String website;


    private int setId() {
        int i = nextId;
        nextId++;
        return i;
    }

    public Candidate(String firstName, String lastName, String party, String website) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.party = party;
        this.website = website;
    }

    public String getName() {
        String name = firstName + " " + lastName;
        return name;
    }

    public String getParty() {
        return this.party;
    }

    public int getId() {
        return this.id;
    }

}
