package codecamp;

public class GameOfLife {

    /*
        Any live cell with less than two live neighbours dies.
        Any live cell with two or three live neighbours remains living.
        Any live cell with more than three live neighbours dies.
        Any dead cell with exactly three live neighbours becomes a live cell.
    */
    private static void gameOfLife(int[][] board, int iterations) {
        int[][] prev = new int[board.length][board[0].length];
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                prev[i][j] = board[i][j];
            }
        }

        for(int iteration = 0; iteration < iterations; iteration++) {
            int[][] today = new int[board.length][board[0].length];
            for(int i = 0; i < board.length; i++) {
                for(int j = 0; j < board[i].length; j++) {
                    businessRule(prev, today, i, j);
                }
            }
            prev = today;
        }
    }

    private static void businessRule(int[][] prev, int[][] today, int i, int j) {

        int liveNeighbors = getLiveNeighbors(prev, i, j);
        if(prev[i][j] == 1) {
            // live cell
            if(liveNeighbors < 2 || liveNeighbors > 3) {
                today[i][j] = 0;
            }
        } else {
            // dead cell
            if(liveNeighbors == 3) {
                today[i][j] = 1;
            }
        }
    }

    private static int getLiveNeighbors(int[][] prev, int i, int j) {
        return  getNeighbor(prev, i-1, j) +
                getNeighbor(prev, i+1, j) +
                getNeighbor(prev, i, j-1) +
                getNeighbor(prev, i, j+1) +
                getNeighbor(prev, i-1, j-1) +
                getNeighbor(prev, i-1, j+1) +
                getNeighbor(prev, i+1, j-1) +
                getNeighbor(prev, i+1, j+1);
    }

    private static int getNeighbor(int[][] prev, int i, int j) {
        if(i >= 0 && i < prev.length && j >= 0 && j < prev[i].length) {
            return prev[i][j];
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        int[][] board = {{0, 1, 0}, {1, 1, 0}, {0, 0, 0}};
        gameOfLife(board, 1);
    }
}
