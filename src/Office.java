public abstract class Office {

    private String name;
    private final boolean isTermLimited;

    public Office(String name, boolean isTermLimited) {
        this.name = name;
        this.isTermLimited = isTermLimited;
    }

}
