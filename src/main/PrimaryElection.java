package main;

import java.util.GregorianCalendar;

public class PrimaryElection extends Election {

    private final int district = 0;
    private final String party;
    private int primaryFor;

    public PrimaryElection(int id, GregorianCalendar date, String office, String party, int district, String type) {
        super(id, date, office, type, district);
        this.party = party;
    }

    public int getDistrict() {
        return district;
    }

    public String getParty() {
        return party;
    }

    public void setPrimaryFor(int generalID) {
        this.primaryFor = generalID;
    }


}
