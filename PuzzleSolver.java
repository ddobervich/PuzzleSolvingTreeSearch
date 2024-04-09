import java.util.*;

public class PuzzleSolver {
    public static final int MAX_DEPTH = 8;
    public static final int[][] ONE_MOVE_TEST = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
    public static final int[][] TWO_MOVE_TEST = {{1, 2, 3}, {4, 0, 6}, {7, 5, 8}};
    public static final int[][] SEVEN_MOVE_TEST = {{1, 2, 3}, {8, 7, 5}, {4, 0, 6}};
    public static final int[][] FOURTEEN_MOVE_TEST = {{4, 2, 5}, {7, 1, 3}, {8, 6, 0}};
    public static final int[][] THIRTY_ONE_MOVE_TEST = {{8, 7, 6}, {5, 4, 3}, {2, 1, 0}};

    public static void main(String[] args) {
        // -----------------------------------------------
        // Use this to check your TileState methods work!
        // -----------------------------------------------
        //humanPlay();

        // -----------------------------------------------
        // Use this to run your implementation of breadth first search
        // -----------------------------------------------
        //bfs(ONE_MOVE_TEST);
    }




    private static void bfs(int[][] initialBoard) {
        TileState initial = new TileState(initialBoard);
        TileState result = bfs(initial);

        if (result == null) {
            System.out.println("No results found.");
            System.out.println(initial);
            return;
        }

        System.out.println("Solved in: " + result.getDepth() + " moves");

        // TODO: display solution path from initial to goal
    }

    private static TileState bfs(TileState initial) {
        System.out.println("Running bfs on " + initial);

        // TODO:  implement breadth first search!

        return null;
    }

    private static void humanPlay() {
        Scanner in = new Scanner(System.in);
        int[][] inital = {{1, 4, 3}, {6, 0, 7}, {5, 8, 2}};

        TileState current = new TileState(inital);
        //TileState current = TileState.getRandomSolvableBoard(4,1);

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
