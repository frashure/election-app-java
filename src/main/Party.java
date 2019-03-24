package main;

import java.util.ArrayList;

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

    public String getShortName() {
        return this.shortName;
    }

    public String getWebsite() {
        return this.website;
    }

//    public static ArrayList<Party> createParties() {
//
//    }
}
