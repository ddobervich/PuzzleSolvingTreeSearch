import sun.font.FontRunIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class TileState {
    private int[][] board;
    private TileState parent;
    private int emptyR, emptyC;
    private int depth;

    private static final int[][] GOAL_STATE  = { {1, 2, 3}, {4, 5, 6}, {7, 8, 0} };

    public TileState(int[][] initial) {
        board = copy(initial);
        setEmptyLocation();
        parent = null;
        this.depth = 0;
    }

    public TileState(TileState toCopy) {
        this.board = copy(toCopy.board);
        this.parent = toCopy.parent;
        this.emptyC = toCopy.emptyC;
        this.emptyR = toCopy.emptyR;
        this.depth = toCopy.depth;
    }

    public ArrayList<TileState> getNextStates() {
        /* TODO: you do this one */
        return null;
    }

    public int[][] getBoard() {
        return this.board;
    }

    private void setEmptyLocation() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == 0) {
                    this.emptyR = r;
                    this.emptyC = c;
                }
            }
        }
    }

    public void setParent(TileState p) {
        this.parent = p;
    }

    public boolean isGoal() {
        return isGoal(this.board);
    }

    public boolean isGoal(int[][] board) {
        return equalBoards(board, GOAL_STATE);
    }

    public void moveTile(int r, int c) {
        if (!isAdjacent(r, c, emptyR, emptyC)) return;

        board[emptyR][emptyC] = board[r][c];
        board[r][c] = 0;
        emptyR = r;
        emptyC = c;
    }

    private boolean isAdjacent(int r, int c, int r2, int c2) {
        int rDiff = Math.abs(r - r2);
        int cDiff = Math.abs(c - c2);
        return rDiff + cDiff == 1;
    }

    @Override
    public boolean equals(Object other) {
        return equalBoards(this.board, ((TileState)other).board);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(emptyR, emptyC);
        result = 31 * result + Arrays.hashCode(board);
        return result;
    }

    public TileState copy() {
        return new TileState(this);
    }

    public String toString() {
        String out = "\n";
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                out += "[ " + board[r][c] + "] ";
            }
            out += "\n";
        }
        out += "--------------------------\n";
        return out;
    }

    public static int[][] getGoalState() {
        return GOAL_STATE;
    }

    private boolean isInBounds(int r, int c) {
        if (r < 0 || c < 0) return false;
        if (r >= board.length || c >= board[0].length) return false;
        return true;
    }

    private int[][] copy(int[][] initial) {
        int[][] copy = new int[initial.length][initial[0].length];
        for (int r = 0; r < initial.length; r++) {
            for (int c = 0; c < initial[r].length; c++) {
                copy[r][c] = initial[r][c];
            }
        }

        return copy;
    }

    private boolean equalBoards(int[][] board, int[][] goal) {
        if (board.length != goal.length || board[0].length != goal[0].length) return false;

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] != goal[r][c]) return false;
            }
        }

        return true;
    }

    public TileState getParent() {
        return this.parent;
    }

    public int getDepth() {
        return this.depth;
    }
}
