import java.util.List;
import java.util.Scanner;

public class StateTester {
    public static void main(String[] args) {
        /*
        --------------------------------------
        Use this to play your game as a human
        ---------------------------------------
         */
        humanPlay();
    }

    private static void humanPlay() {
        Scanner in = new Scanner(System.in);
        int[][] inital = {{1, 4, 3}, {6, 0, 7}, {5, 8, 2}};

        TileState current = new TileState(inital);

        System.out.println(current);
        while (!current.isGoal()) {
            System.out.println(current);
            System.out.println("\n");
            System.out.println("Which would you like?");
            List<TileState> next = current.getNextStates();
            for (int i = 0; i < next.size(); i++) {
                System.out.println("Type " + i + " for: ");
                System.out.println(next.get(i));
            }
            int choice = in.nextInt();

            if (0 <= choice && choice < next.size()) {
                current = next.get(choice);
            }
        }
    }
}
