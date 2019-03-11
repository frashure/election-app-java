import java.util.GregorianCalendar;

public class PrimaryElection extends Election {

    private final int district;

    public PrimaryElection(GregorianCalendar date, Office office, int district) {
        super(date, office);
        this.district = district;
    }

}
