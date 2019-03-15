public class Office {

    private String name;
    private final boolean isTermLimited;

    public Office(String name) {
        this.name = name;
        isTermLimited = false;
    }

    public Office(String name, boolean isTermLimited) {
        this.name = name;
        this.isTermLimited = isTermLimited;
    }

    public String getName() {
        return this.name;
    }

}
