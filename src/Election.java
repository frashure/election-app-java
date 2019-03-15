import java.util.ArrayList;
import java.util.GregorianCalendar;

public abstract class Election {

    private static int nextId = 1;
    private final int id = setId();
    private final GregorianCalendar date;
    private Office office;
    private ArrayList<Candidate> candidates = new ArrayList<>();


    public Election(GregorianCalendar date, Office office) {
        this.date = date;
        this.office = office;
    }

    private int setId() {
        int i = nextId;
        nextId++;
        return i;
    }


    public GregorianCalendar getDate() {
        return this.date;
    }

    public Office getOffice() {
        return this.office;
    }

    public int getId() {
        return this.id;
    }

    public addCandidate(Candidate c) {
        this.candidates.add(c);
    }

    public ArrayList getCandidates() {
        return this.candidates;
    }
}
