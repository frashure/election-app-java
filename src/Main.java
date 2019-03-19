import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {



    public static void main(String[] args) throws SQLException, ParseException {


        ArrayList<Election> elections = Election.getElections();

        for (Election e : elections) {
            System.out.println("Office: " + e.getOffice().getName());
            System.out.println("Type: " + e.getType());
            System.out.println("Primary?: " + (e.getType().equalsIgnoreCase("primary")));
            if (e.getType().equalsIgnoreCase("primary")) {
                System.out.println("Party: " + ((PrimaryElection) e).getParty().getShortName());
            }
            System.out.println("ID: " + e.getId());
            Date dateToFormat = e.getDate().getTime();
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
            String formattedDate = sdf.format(dateToFormat);
            System.out.println("Date: " + formattedDate + "\n");
        }


    }
}
