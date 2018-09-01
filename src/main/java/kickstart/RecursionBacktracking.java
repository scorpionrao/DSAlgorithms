package kickstart;

import java.util.List;

public class RecursionBacktracking {

    public class Board {

        public List<Object> cells;
        public List<Object> choicesForEachCell;
        public void make(Object choiceForCell){};
        public void unMake(Object choiceForCell){};

        public boolean isGoalState() {
            // Is the end state OK ?
            return false;
        }

        public boolean isSafe(Object choiceForCell) {
            // Is it OK to make this choiceForCell per constraints ?
            return false;
        }

        public boolean isAllCellSolved() {
            return cells.isEmpty();
        }
    }

    public class Game {

        public boolean recursionBacktracking(Board board) {

            // BASE CASE
            if (board.isAllCellSolved()) {
                return board.isGoalState();
            }

            for (Object choiceForCell : board.choicesForEachCell) {
                if (board.isSafe(choiceForCell)) {
                    board.make(choiceForCell);
                    if (recursionBacktracking(board)) {
                        return true;
                    }
                    board.unMake(choiceForCell);
                }
            }
            return false;
        }
    }
}
