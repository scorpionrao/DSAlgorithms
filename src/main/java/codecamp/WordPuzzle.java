package codecamp;

import java.util.Arrays;

public class WordPuzzle {

    private static boolean approach1(char[][] puzzle, String search) {
        for(int i = 0; i < puzzle.length; i++) {
            for(int j = 0; j < puzzle[i].length; j++) {
                if(recurse(puzzle, search, i, j, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean recurse(char[][] puzzle, String search, int i, int j, int searchIndex) {

        if(i >= puzzle.length || j >= puzzle[i].length) {
            return false;
        }

        if(search.charAt(searchIndex) != puzzle[i][j]) {
            return false;
        }

        if(searchIndex == search.length() - 1) {
            return true;
        }

        return recurse(puzzle, search, i, j + 1, searchIndex + 1) ||
               recurse(puzzle, search, i + 1, j, searchIndex + 1);
    }

    private static void evaluate(char[][] puzzle, String search, boolean expected) {
        System.out.println("INPUT");
        for(char[] row : puzzle) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("Search : " + search);
        System.out.println("Expected : " + expected);
        boolean result1 = approach1(puzzle, search);
        System.out.println("Approach1 : " + result1);
    }

    public static void main(String[] args) {
        char[][] puzzle = {{'F', 'A', 'C', 'I'},
                           {'O', 'B', 'Q', 'P'},
                           {'A', 'N', 'O', 'B'},
                           {'M', 'A', 'S', 'S'}};
        evaluate(puzzle, "MASS", true);
        evaluate(puzzle, "FOAM", true);
    }
}
