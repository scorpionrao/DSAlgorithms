package leetcode;

import java.util.Stack;

public class SimilarColorMaxNeighbors {

    public static class Cell {
        int row;
        int col;
        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static int similarColorMaxNeighborsIterative(String[][] grid) {

        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int maxLength = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                if(!visited[i][j]) {
                    int count = 0;
                    Stack<Cell> stack = new Stack();
                    stack.push(new Cell(i, j));

                    while(!stack.isEmpty()) {
                        Cell cell = stack.pop();
                        count++;
                        visited[cell.row][cell.col] = true;
                        addNeighbors(cell, grid, stack, visited);
                    }
                    maxLength = Math.max(maxLength, count);

                }
            }
        }
        return maxLength;
    }

    private static void addNeighbors(Cell cell, String[][] grid, Stack<Cell> stack, boolean[][] visited) {
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for(int[] dir : dirs) {
            int neighborRow = cell.row + dir[0];
            int neighborCol = cell.col + dir[1];
            if(isValid(neighborRow, neighborCol, grid)
                    && !visited[neighborRow][neighborCol]
                    && grid[neighborRow][neighborCol] == grid[cell.row][cell.col]) {

                stack.push(new Cell(neighborRow, neighborCol));
            }
        }
    }

    private static boolean isValid(int row, int col, String[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[row].length;
    }

    private static int similarColorMaxNeighborsRecursive(String[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int maxLength = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(!visited[i][j]) {
                    maxLength = Math.max(maxLength, dfs(i, j, grid[i][j], grid, visited));
                }
            }
        }
        return maxLength;
    }

    private static int dfs(int i, int j, String currentColor, String[][] grid, boolean[][] visited) {
        if(!isValid(i, j, grid) || visited[i][j] || grid[i][j] != currentColor) {

            return 0;
        }

        visited[i][j] = true;
        return 1 +
                dfs(i-1, j, currentColor, grid, visited) +
                dfs(i+1, j, currentColor, grid, visited) +
                dfs(i, j-1, currentColor, grid, visited) +
                dfs(i, j+1, currentColor, grid, visited);
    }

    public static void main(String[] args) {
        String[][] colorGrid = {
                {"G", "G", "B", "R"},
                {"G", "B", "R", "B"},
                {"R", "B", "B", "B"}
        };
        System.out.println(similarColorMaxNeighborsIterative(colorGrid));
        System.out.println(similarColorMaxNeighborsRecursive(colorGrid));
    }
}