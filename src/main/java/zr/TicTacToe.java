package zr;

public class TicTacToe {

    /*
        Implement Tic Tac Toe game.
        void ticTacToe(char[ ][ ] board, char A, char B)
        if A wins, print “Winner: A”
        if B wins, print “Winner: B”

        ******************************************
        Game:
            *** Method 1 ***
            play(board) {
                while(empty cells are available) {
                    One of the two players play
                    if(isThereWinner(board, PlayerA, PlayerB)) {
                        printWinner
                    }
                }
            }

            *** Method 2 ***
            isThereWinner(board, PlayerA, PlayerB) {
                return isValid(board) && winnerExists
            }

            *** Method 3 ***
            printWinner(board)
        *******************************************

        16 ways to win:

        BOARD IS VALID ??
            - entries match characters
            - same (~1) number of turns.

        WINS ??

                                            Player B
                  -------------------------------------------------------------------
                            YES                             NO
                  -------------------------------------------------------------------
                |
                |    YES     *Invalid*                       8 ways (ends In A turn)
                |
        Player A|
                |
                |   NO      8 ways (ends In B turn)        OK
                |
                |

        Data Structure:
            - How to represent board (INPUT) - char[][]
            - How to represent entries (INPUT) - char
            - How to represent turns ? - int (0 = equal turns, 1 = unequal turns)
            - How to represent Player A wins
                    - row1 = 3, row2 = 3, row3 = 3, col1 = 3, col2 = 3, col3 = 3, diag = 3, adiag = 3.
                    - row[i] = 3, col[i] = 3, diag = 3, adiag = 3.
            - How to represent Player B wins ?
                    - row1 = -3, row2 = -3, row3 = -3, col1 = -3, col2 = -3, col3 = -3, diag = -3, adiag = -3.
                    - row[i] = -3, col[i] = -3, diag = -3, adiag = -3.

    */

    private static void ticTacToe(String name, char[][] board, char firstPlayer, char secondPlayer) {

        System.out.println(name);
        /* Bad board, length varies, same symbols */
        if(board == null || board.length != 3 || board[0].length != 3 || firstPlayer == secondPlayer) {
            System.out.println("Invalid Inputs");
            return;
        }

        int turns = 0;
        int[] rows = new int[3];
        int[] cols = new int[3];
        int diag = 0;
        int adiag = 0;

        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board[0].length; col++) {
                if(board[row][col] == firstPlayer) {
                    turns++;
                    rows[row]++;
                    cols[col]++;
                    if(row == col) {
                        diag++;
                    }
                    if(row + col == 2) {
                        adiag++;
                    }
                } else if(board[row][col] == secondPlayer) {
                    turns--;
                    rows[row]--;
                    cols[col]--;
                    if(row == col) {
                        diag--;
                    }
                    if(row + col == 2) {
                        adiag--;
                    }
                } else {
                    // invalid characters in the board
                    System.out.println("Invalid Player entries");
                }
            }
        }

        boolean playerAWin = rows[0] == 3 || rows[1] == 3 || rows[2] == 3 ||
                cols[0] == 3 || cols[1] == 3 || cols[2] == 3 ||
                diag == 3 || adiag == 3;

        boolean playerBWin = rows[0] == -3 || rows[1] == -3 || rows[2] == -3 ||
                cols[0] == -3 || cols[1] == -3 || cols[2] == -3 ||
                diag == -3 || adiag == -3;

        if(turns != 0 && turns != 1 && turns != -1) {
            System.out.println("Unequal number of entries");
            return;
        }

        if(playerAWin && turns != 1) {
            System.out.println("Player A cannot win in other person turn");
            return;
        }

        if(playerBWin && turns != 0) {
            System.out.println("Player B cannot win in other person turn");
            return;
        }

        if(playerAWin && playerBWin) {
            System.out.println("Both player cannot win");
            return;
        }

        if(playerAWin) {
            System.out.println("Winner: Player A");
        } else if(playerBWin) {
            System.out.println("Winner: Player B");
        } else {
            System.out.println("Unknown state should not be reached");
        }
    }

    public static void main(String[] args) {
        ticTacToe("Board1", null, 'a', 'b');
        char[][] board2 = {{'a', 'b', 'a'}, {'b', 'a', 'b'}, {'a', 'b', 'a'}};
        ticTacToe("Board2", board2, 'a', 'b');
    }

}
