public class Party {

    private final String shortName;
    private final String name;
    private final String website;

    public Party(String shortName) {
        this.shortName = shortName;
        this.name = null;
        this.website = null;
    }

    public Party(String shortName, String name, String website) {
        this.shortName = shortName;
        this.name = name;
        this.website = website;
    }

    public String getName() {
        return this.name;
    }

    public String getWebsite() {
        return this.website;
    }

}
