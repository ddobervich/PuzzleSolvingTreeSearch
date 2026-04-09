import java.lang.reflect.Array;
import java.util.*;

public class PuzzleSolver {
    public static final int[][] ONE_MOVE_TEST = {{1, 2, 3}, {4, 5, 6}, {7, 0, 8}};
    public static final int[][] TWO_MOVE_TEST = {{1, 2, 3}, {4, 0, 6}, {7, 5, 8}};
    public static final int[][] SEVEN_MOVE_TEST = {{1, 2, 3}, {8, 7, 5}, {4, 0, 6}};
    public static final int[][] FOURTEEN_MOVE_TEST = {{4, 2, 5}, {7, 1, 3}, {8, 6, 0}};
    public static final int[][] THIRTY_ONE_MOVE_TEST = {{8, 7, 6}, {5, 4, 3}, {2, 1, 0}};

    public static final int[][] LARGER_TEST =     {
            { 1,  2,  3,  4},
            { 5,  6,  7,  8},
            {13,  9, 10, 12},
            { 0, 14, 11, 15}
    };

    public static final int[][] LARGER_SIXTEEN_MOVES =     {
            { 5,  1,  7,  3},
            { 9,  2, 11,  4},
            {13,  6, 15,  8},
            {10,  0, 14, 12}
    };

    public static final int[][] LARGER_44_MOVES =     {
            {13,  1,  3, 15},
            { 5,  7,  2, 14},
            { 6,  4,  0,  8},
            { 9, 10, 11, 12}
    };

    public static final int[][] LARGER_46_MOVES =    {
            {10,  2,  6, 15},
            { 1,  5,  9, 12},
            {13,  4,  0, 14},
            {11,  8,  3,  7}
    };

    public static final int[][] HARDEST = {
            { 0, 12,  9, 13},
            {15, 11, 10, 14},
            { 3,  7,  2,  5},
            { 4,  8,  6,  1}
    };

    public static void main(String[] args) {
        TileState initial = new TileState(LARGER_TEST);   // use a board to make an initial TileState
        TileState result = dfs(initial, 5);           // solve it!

        if (result == null) {
            System.out.println("No results found.");
            System.out.println(initial);
            return;
        } else {
            System.out.println("Solved in: " + result.getDepth() + " moves");
            printSolution(result);
        }
    }

    private static TileState greedySearch(TileState initial) {
        System.out.println("Running greedy search on " + initial);

        ArrayList<TileState> toVisit = new ArrayList<>();
        toVisit.add(initial);

        while (toVisit.size() > 0) {
            TileState current = toVisit.remove(0);

            if (current.isGoal()) {
                return current;
            }

            ArrayList<TileState> nextStates = current.getNextStates();

            for (TileState next : nextStates) {
                /*
                YOU CHANGE THIS BIT.
                1).  Make sure you've made a .getScore() method inside TileState
                2).  Use nextStates.add( index, next ) to insert the new states in sorted order
                 */
            }
        }

        return null;
    }

    private static void printSolution(TileState result) {
        ArrayList<TileState> solution = new ArrayList<>();
        solution.add(result);

        while (result != null) {
            result = result.getParent();
            solution.add(result);
        }
        solution.remove(solution.size() - 1);  // remove the null at the end

        Collections.reverse(solution);
        System.out.println(solution);
    }

    private static TileState bfs(TileState initial) {
        System.out.println("Running bfs on " + initial);

        ArrayList<TileState> toVisit = new ArrayList<>();
        toVisit.add(initial);

        while (toVisit.size() > 0) {
            TileState current = toVisit.remove(0);

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

        while (toVisit.size() > 0) {
            TileState current = toVisit.remove(toVisit.size() - 1);

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
