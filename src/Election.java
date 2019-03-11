import java.util.GregorianCalendar;

public abstract class Election {

    private static int nextId = 1;
    private final int id = setId();
    private final GregorianCalendar date;
    private Office office;


    public Election(GregorianCalendar date, Office office) {
        this.date = date;
        this.office = office;
    }

    private int setId() {
        int i = nextId;
        nextId++;
        return i;
    }

}
