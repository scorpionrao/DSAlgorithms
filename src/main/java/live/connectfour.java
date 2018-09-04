package live;

public class Connectfour
{

    static class Player {

        private String symbol;

        Player(String symbol) {
            this.symbol = symbol;
        }

        String getSymbol() {
            return this.symbol;
        }

        void play(Game game, int columnIndex) {
            game.getBoard().drop(columnIndex, this);
        }

        boolean isMySymbol(String symbol) {
            return this.symbol == symbol;
        }

    }

    static class Board {

        private static final String EMPTY = "-";
        private static final int CONSECUTIVE_FOR_WIN = 4;
        private String[][] grid;

        Board(int numRows, int numCols) {
            grid = new String[numRows][numCols];
            createBoard();
        }

        private void createBoard() {
            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[0].length; j++) {
                    grid[i][j] = EMPTY;
                }
            }
        }

        private void printBoard() {
            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < grid[0].length; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }
        }

        void drop(int colIndex, Player player) {
            for(int i = grid.length - 1; i >= 0; i--) {
                if(grid[i][colIndex] == EMPTY) {
                    grid[i][colIndex] = player.getSymbol();
                    if(didWin(player)) {
                        System.out.println("Won: " + player.getSymbol());
                    }
                    break;
                }
            }
        }

        private boolean didWin(Player player) {

            // row
            for(int i = 0; i < grid.length; i++) {
                for(int j = 0; j < (grid[0].length - CONSECUTIVE_FOR_WIN + 1); j++) {
                    if(player.isMySymbol(grid[i][j])
                        && player.isMySymbol(grid[i][j+1])
                        && player.isMySymbol(grid[i][j+2])
                        && player.isMySymbol(grid[i][j+3])) {

                        return true;
                    }
                }
            }

            for(int j = 0; j < grid[0].length; j++) {
                for(int i = 0; i < (grid.length - CONSECUTIVE_FOR_WIN + 1); i++) {
                    if(player.isMySymbol(grid[i][j])
                            && player.isMySymbol(grid[i+1][j])
                            && player.isMySymbol(grid[i+2][j])
                            && player.isMySymbol(grid[i+3][j])) {

                        return true;
                    }
                }
            }

            for(int i = 0; i <= (grid.length - CONSECUTIVE_FOR_WIN); i++) {
                for(int j = 0; j <= (grid[0].length - CONSECUTIVE_FOR_WIN); j++) {
                    if(player.isMySymbol(grid[i][j])
                            && player.isMySymbol(grid[i+1][j+1])
                            && player.isMySymbol(grid[i+2][j+2])
                            && player.isMySymbol(grid[i+3][j+3])) {

                        return true;
                    }
                }
            }

            for(int i = grid.length - 1; i >= (grid.length - CONSECUTIVE_FOR_WIN + 1); i--) {
                for(int j = 0; j <= (grid[0].length - CONSECUTIVE_FOR_WIN); j++) {
                    if(player.isMySymbol(grid[i][j])
                            && player.isMySymbol(grid[i-1][j+1])
                            && player.isMySymbol(grid[i-2][j+2])
                            && player.isMySymbol(grid[i-3][j+3])) {

                        return true;
                    }
                }
            }
            return false;
        }
    }

    public static class Game {
        private Board board;
        public Game() {
            board = new Board(6, 7);
        }

        public Board getBoard() {
            return this.board;
        }

        public void printGame() {
            this.board.printBoard();
        }
    }

    public static void main(String[] args) {

        Game game = new Game();

        Player redPlayer = new Player("R");
        Player bluePlayer = new Player("B");

        bluePlayer.play(game, 2);
        bluePlayer.play(game, 2);
        bluePlayer.play(game, 2);
        redPlayer.play(game, 2);

        bluePlayer.play(game, 3);
        bluePlayer.play(game, 3);
        redPlayer.play(game, 3);

        bluePlayer.play(game, 4);
        redPlayer.play(game, 4);

        redPlayer.play(game, 5);

        game.printGame();
    }
}
