import java.util.ArrayList;
import java.util.GregorianCalendar;

public class PrimaryElection extends Election {

    private final int district;
    private final Party party;

    public PrimaryElection(GregorianCalendar date, Office office, Party party, int district) {
        super(date, office);
        this.district = district;
        this.party = party;
    }

    public int getDistrict() {
        return district;
    }

    public Party getParty() {
        return party;
    }
}
