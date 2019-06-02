package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class Election {

    private final int id;
    private final String type;
    private final GregorianCalendar date;
    private String office;
    private ArrayList<Candidate> candidates = new ArrayList<>();
    private int district;


    public Election(int id, GregorianCalendar date, String office, String type, int district) {
        this.id = id;
        this.date = date;
        this.office = office;
        this.type = type;
        this.district = district;
    }


    public GregorianCalendar getDate() {
        return this.date;
    }

    public String getOffice() {
        return this.office;
    }

    public int getDistrict() {
        return this.district;
    }

    public int getId() {
        return this.id;
    }

    public void addCandidate(Candidate c) {
        this.candidates.add(c);
    }

    public ArrayList getCandidates() {
        return this.candidates;
    }

    public String getType() {
        return this.type;
    }

    public static GregorianCalendar dateConverter(String date) throws ParseException {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = date;
            Date newDate = sdf.parse(dateString);
            Calendar c = Calendar.getInstance();
            c.setTime(newDate);

            int y = c.get(Calendar.YEAR);
            int m = c.get(Calendar.MONTH);
            int d = c.get(Calendar.DAY_OF_WEEK);

            GregorianCalendar gc = new GregorianCalendar(y, m, d);
            return gc;
        } catch (ParseException e) {
            throw e;
        }


    }

//    public static Election create() {
//        if
//    }

}
