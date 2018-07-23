package zr;

public class TicTacToe {

    /*
        Implement Tic Tac Toe game.
        void ticTacToe(char[ ][ ] board, char A, char B)

        if A wins, print “Winner: A”
        if B wins, print “Winner: B”

        8 ways to win - row1, row2, row3, col1, col2, col3, diag, adiag

        +1 for Player A, -1 for Player B.
        +3 is win for Player A, -3 is win for Player B.
        At the end, both players should have equal number of turns (SEPARATE BOOLEAN TRACKER)

        Win should occur on players turn.
        Both cannot win as game should have stopped as soon as FIRST win occurred.

    */

    public boolean valid(char[][] board, char playerA, char playerB) {

        if(board == null || board.length != 3 || board[0].length != 3) {
            return false;
        }

        int turns = 0;
        int[] rows = new int[3];
        int[] cols = new int[3];
        int diag = 0;
        int adiag = 0;

        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == playerA) {
                    turns++;
                    rows[i]++;
                    cols[j]++;
                    if(i == j) {
                        diag++;
                    }
                    if(i + j == 2) {
                        adiag++;
                    }
                } else if(board[i][j] == playerB) {
                    turns--;
                    rows[i]--;
                    cols[j]--;
                    if(i == j) {
                        diag--;
                    }
                    if(i + j == 2) {
                        adiag--;
                    }
                } else {
                    // invalid characters in the board
                    return false;
                }
            }
        }

        boolean playerAWin = rows[0] == 3 || rows[1] == 3 || rows[2] == 3 ||
                         cols[0] == 3 || cols[1] == 3 || cols[2] == 3 ||
                         adiag == 3 || adiag == 3;

        boolean playerBWin = rows[0] == -3 || rows[1] == -3 || rows[2] == -3 ||
                cols[0] == -3 || cols[1] == -3 || cols[2] == -3 ||
                adiag == -3 || adiag == -3;

        // skewed number of turns
        if(turns != 0 && turns != 1) {
            return false;
        }

        if(playerAWin && turns != 1) {
            return false;
        }

        if(playerBWin && turns != 0) {
            return false;
        }

        if(playerAWin && playerBWin) {
            return false;
        }
        return true;
    }

}
