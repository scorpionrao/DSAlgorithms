package codecamp;

import java.util.Arrays;

public class ChessKnight {

    private static int[][] dirs = { {1, 2}, {-1, 2},
                                    {2, 1}, {2, -1},
                                    {1, -2}, {-1, -2},
                                    {-2, 1}, {-2, -1}
                                  };

    /*
        while there are untried tours {
            generate the next tour
            if this tour covers all squares {
                print this path;
            }
        }
     */
    private static int numberOfToursApproach1(int size) {

        int[] result = new int[1];

        int[][] visited = new int[size][size];
        for(int[] row : visited) {
            Arrays.fill(row, -1);
        }
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                dfs(i, j, visited, 1, result);
            }
        }

        return result[0];
    }

    private static void dfs(int x, int y, int[][] visited, int depth, int[] result) {

        if(isNotSafe(x, y, visited)) {
            return;
        }

        if(hasVisitedAllCells(depth, visited, x)) {
            visited[x][y] = depth;
            //printBoard(visited);
            result[0]++;
            visited[x][y] = -1;
            return;
        }

        visited[x][y] = depth;
        for(int[] dir : dirs) {
            dfs(x+dir[0], y+dir[1], visited, depth+1, result);
        }
        visited[x][y] = -1;
    }

    private static boolean isNotSafe(int x, int y, int[][] visited) {
        return x < 0 || y < 0 || x >= visited.length || y >= visited[x].length || visited[x][y] != -1;
    }

    private static boolean hasVisitedAllCells(int depth, int[][] visited, int x) {
        return depth == visited.length * visited[x].length;
    }

    private static int numberOfToursApproach2(int size) {

        int[] result = new int[1];

        int[][] visited = new int[size][size];
        for(int[] row : visited) {
            Arrays.fill(row, -1);
        }
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                dfsBacktrack(i, j, visited, 1, result);
            }
        }

        return result[0];
    }

    private static void dfsBacktrack(int x, int y, int[][] visited, int depth, int[] result) {
        if(hasVisitedAllCells(depth, visited, x)) {
            visited[x][y] = depth;
            //printBoard(visited);
            result[0]++;
            visited[x][y] = -1;
            return;
        }

        for(int[] dir : dirs) {
            if(!isNotSafe(x+dir[0], y+dir[1], visited)) {
                visited[x][y] = depth;
                dfs(x+dir[0], y+dir[1], visited, depth+1, result);
                visited[x][y] = -1;
            }
        }
    }

    private static void printBoard(int[][] visited) {
        System.out.println("SOLUTION");
        for(int[] row : visited) {
            System.out.println(Arrays.toString(row));
        }
    }

    private static void evaluate(int size, int expected) {
        System.out.println("Board : " + size + " * " + size);
        System.out.println("Expected : " + expected);
        int result1 = numberOfToursApproach1(size);
        System.out.println("Approach 1 : " + result1);
        int result2 = numberOfToursApproach2(size);
        System.out.println("Approach 2 : " + result2);
        System.out.println("********************");
    }

    public static void main(String[] args) {
        /*
        evaluate(1, 1);
        evaluate(2, 0);
        evaluate(3, 0);
        evaluate(4, 0);
        */
        evaluate(5, 1728);
        /*
        evaluate(6, 100000);
        evaluate(7, 1500);
        evaluate(8, 2000);
        */
    }
}
