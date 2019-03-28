package util;

public class DateFormatter {

    public static int parseYear(String date) {
        String[] s = date.split("-");
        int year = Integer.parseInt(s[0]);
        return year;
    }

}
