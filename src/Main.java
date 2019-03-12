import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        ArrayList<Candidate> candidates = new ArrayList<>();
        boolean cont = true;

        while (cont) {
            Scanner in = new Scanner(System.in);

            System.out.println("CANDIDATE CREATOR:\nEnter candidate first name: ");
            String fName = in.nextLine();
            System.out.println("Last name: ");
            String lName = in.nextLine();
            System.out.println("Party: ");
            String partyName = in.nextLine();


            Party p = new Party("na", partyName, null);
            Candidate c = new Candidate(fName, lName, p, null);

            candidates.add(c);

            System.out.println("Would you like to add more candidates? (y/n)");
            String response = in.nextLine();

            if (response.toLowerCase().charAt(0) == 'n') {
                cont = false;
            }
        }

        for (Candidate c : candidates) {
            System.out.println("ID: " + c.getId());
            System.out.println("Name: " + c.getName());
            System.out.println("Party: " + c.getParty() + "\n");
        }
    }
}
