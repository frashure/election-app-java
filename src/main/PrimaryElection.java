package main;

import java.util.GregorianCalendar;

public class PrimaryElection extends Election {

    private final String party;
    private int primaryFor;

    public PrimaryElection(int id, GregorianCalendar date, String office, String party, int district, String type) {
        super(id, date, office, type, district);
        this.party = party;
    }

    public String getParty() {
        return party;
    }

    public void setPrimaryFor(int generalID) {
        this.primaryFor = generalID;
    }


}
