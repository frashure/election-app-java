import java.util.GregorianCalendar;

public abstract class Election {

    private static int nextId = 1;
    private final int id = setId();
    private final GregorianCalendar date = new GregorianCalendar();


    private int setId() {
        int i = nextId;
        nextId++;
        return i;
    }

}
