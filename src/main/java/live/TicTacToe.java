package live;


import java.util.Scanner;

/*
    How to systematically approach the problem ?

    - Board class
    - Initialize Board
    - Print Board (Testing)
    - Place and Print for Human
    - Place and Print for AI
    - Is Board Full
    - Check For Win
        - Check Rows for Win, Cols for Win, Diagonal for Win, ADiagonal for Win

 */
public class TicTacToe {

    public static class Board {

        private static int NUM_ROWS = 3;
        private static int NUM_COLS = 3;
        private static int cellsTaken = 0;
        private static char currentPlayerMark;

        char[][] puzzle = new char[NUM_ROWS][NUM_COLS];

        Board() {
            initializeBoard();
        }

        public void initializeBoard() {
            for(int i = 0; i < puzzle.length; i++) {
                for(int j = 0; j < puzzle[i].length; j++) {
                    puzzle[i][j] = '-';
                }
            }
        }

        public void printBoard() {
            for(int i = 0; i < puzzle.length; i++) {
                for(int j = 0; j < puzzle[i].length; j++) {
                    System.out.print(puzzle[i][j]);
                    if(j != puzzle[i].length - 1) {
                        System.out.print("|");
                    }
                }
                System.out.println();
            }
            System.out.println();
        }

        public void changePlayer() {
            if(currentPlayerMark == 'X') {
                currentPlayerMark = 'O';
            } else {
                currentPlayerMark = 'X';
            }
        }

        public boolean placeToken(int row, int col) {
            if(row < 0 || col < 0 || row > puzzle.length - 1 || col > puzzle.length - 1 || puzzle[row][col] != '-') {
                //throw new IllegalArgumentException();
                return false;
            }
            puzzle[row][col] = currentPlayerMark;
            cellsTaken++;
            return true;
        }

        public void placeTokenForAI() {
            for(int i = 0; i < puzzle.length; i++) {
                for(int j = 0; j < puzzle[i].length; j++) {
                    if(puzzle[i][j] == '-') {
                        placeToken(i, j);
                        return;
                    }
                }
            }
            // AI cannot make a move
            if(!isFull()) {
                throw new IllegalArgumentException("AI cannot make a move");
            }
        }

        public boolean isFull() {
            return cellsTaken == NUM_ROWS * NUM_COLS;
        }

        public boolean checkForWin() {
            // row check
            for(int i = 0; i < puzzle.length; i++) {
                int score = 0;
                for (int j = 0; j < puzzle[i].length; j++) {
                    if(puzzle[i][j] == 'X') {
                        score++;
                    } else {
                        score--;
                    }
                }
                if(Math.abs(score) == 3) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TicTacToe.Board board = new TicTacToe.Board();

        /*
            Play until no winner yet and board is not full
                Play until there is valid move
                Change Player

            No Winner and Board is full - Tie
            Else Print Winner
         */

        // until no wins AND board is not full
        do {
            board.printBoard();
            int row, col;
            do {
                System.out.println("Player " + board.currentPlayerMark);
                row = scanner.nextInt();
                col = scanner.nextInt();
            } while (!board.placeToken(row, col));
            board.changePlayer();

            // *********************
            board.placeTokenForAI();
            board.changePlayer();
            // *********************
        } while(!board.checkForWin() && !board.isFull());

        if(board.isFull() && !board.checkForWin()) {
            System.out.println("Game is tied");
        } else {
            System.out.println("Final Layout");
            board.printBoard();
            board.changePlayer();
            System.out.println("Player : " + board.currentPlayerMark + " Wins!");
        }
    }
}
