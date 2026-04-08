import java.lang.reflect.Array;
import java.util.*;

public class PuzzleSolver {
    public static final int[][] ONE_MOVE_TEST = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
    public static final int[][] TWO_MOVE_TEST = {{1, 2, 3}, {4, 0, 6}, {7, 5, 8}};
    public static final int[][] SEVEN_MOVE_TEST = {{1, 2, 3}, {8, 7, 5}, {4, 0, 6}};
    public static final int[][] FOURTEEN_MOVE_TEST = {{4, 2, 5}, {7, 1, 3}, {8, 6, 0}};
    public static final int[][] THIRTY_ONE_MOVE_TEST = {{8, 7, 6}, {5, 4, 3}, {2, 1, 0}};

    public static void main(String[] args) {
        // -----------------------------------------------
        // Use this to run your implementation of breadth first search
        // -----------------------------------------------
        TileState initial = new TileState(THIRTY_ONE_MOVE_TEST);   // use a board to make an initial TileState
        TileState result = dfs(initial, 31);           // solve it!

        if (result == null) {
            System.out.println("No results found.");
            System.out.println(initial);
            return;
        } else {
            System.out.println("Solved in: " + result.getDepth() + " moves");
            printSolution(result);
        }
    }

    private static void printSolution(TileState result) {
        ArrayList<TileState> solution = new ArrayList<>();
        solution.add(result);

        while (result != null) {
            result = result.getParent();
            solution.add(result);
        }
        solution.remove( solution.size()-1);  // remove the null at the end

        Collections.reverse(solution);
        System.out.println(solution);
    }

    private static TileState bfs(TileState initial) {
        System.out.println("Running bfs on " + initial);

        ArrayList<TileState> toVisit = new ArrayList<>();
        toVisit.add(initial);

        while( toVisit.size() > 0 ) {
            TileState current = toVisit.remove( 0 );

            if (current.isGoal()) {
                return current;
            }

            ArrayList<TileState> nextStates = current.getNextStates();

            for (TileState next : nextStates) {
                toVisit.add(next);
            }
        }

        return null;
    }

    private static TileState dfs(TileState initial, int MAX_DEPTH) {
        System.out.println("Running dfs on " + initial);

        ArrayList<TileState> toVisit = new ArrayList<>();
        toVisit.add(initial);

        while( toVisit.size() > 0 ) {
            TileState current = toVisit.remove( toVisit.size()-1);

            if (current.isGoal()) {
                return current;
            }

            if (current.getDepth() < MAX_DEPTH) {
                ArrayList<TileState> nextStates = current.getNextStates();
                for (TileState next : nextStates) {
                    toVisit.add(next);
                }
            }
        }

        return null;
    }
}
