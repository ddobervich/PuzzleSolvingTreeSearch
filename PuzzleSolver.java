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
        // Use this to run your implementation of breadth first search
        // -----------------------------------------------
        TileState initial = new TileState(ONE_MOVE_TEST);   // use a board to make an initial TileState
        TileState result = bfs(initial);                    // solve it!

        if (result == null) {
            System.out.println("No results found.");
            System.out.println(initial);
            return;
        } else {
            System.out.println("Solved in: " + result.getDepth() + " moves");
            // TODO: display solution path from initial to goal
        }
    }

    private static TileState bfs(TileState initial) {
        System.out.println("Running bfs on " + initial);

        // TODO:  implement breadth first search!

        return null;
    }
}
