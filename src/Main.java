public class Main {


    public static void main(String[] args) {

        Candidate[] candidates = new Candidate[2];

        candidates[0] = new Candidate("Donald", "Trump", "Republican", null);
        candidates[1] = new Candidate("Hillary", "Clinton", "Democratic", null);

        for (Candidate c : candidates) {
            System.out.println("ID: " + c.getId());
            System.out.println("Name: " + c.getName());
            System.out.println("Party: " + c.getParty() + "\n");
        }
    }
}
