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
    private Office office;
    private ArrayList<Candidate> candidates = new ArrayList<>();


    public Election(int id, GregorianCalendar date, Office office, String type) {
        this.id = id;
        this.date = date;
        this.office = office;
        this.type = type;
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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = date;
        Date newDate = sdf.parse(dateString);
        Calendar c = Calendar.getInstance();
        c.setTime(newDate);

        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH);
        int d =  c.get(Calendar.DAY_OF_WEEK);

        GregorianCalendar gc = new GregorianCalendar(y, m, d);
        return gc;
    }



}
