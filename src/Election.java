public class Election {

    private static int nextId = 0;
    private final int id = setId();


    private int setId() {
        int i = nextId;
        nextId++;
        return i;
    }

}
