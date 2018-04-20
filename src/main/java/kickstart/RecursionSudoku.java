package kickstart;

public class RecursionSudoku {

    private static boolean solve(int[][] dataStructure) {
        int[] nextUnassigned = {-1, -1};
        if(!hasUnassignedCell(dataStructure, nextUnassigned)) {
            return true;
        }
        for(int option = 1; option < 10; option++) {
            if(isSafe(dataStructure, nextUnassigned, option)) {
                dataStructure[nextUnassigned[0]][nextUnassigned[1]] = option;
                if(solve(dataStructure)) {
                    return true;
                }
                dataStructure[nextUnassigned[0]][nextUnassigned[1]] = 0;
            }
        }
        // backtracking
        return false;
    }

    private static boolean isSafe(int[][] dataStructure, int[] nextUnassigned, int option) {
        // row check
        for(int col = 0; col < dataStructure.length; col++) {
            if(dataStructure[nextUnassigned[0]][col] == option) {
                return false;
            }
        }
        // col check
        for(int row = 0; row < dataStructure.length; row++) {
            if(dataStructure[row][nextUnassigned[1]] == option) {
                return false;
            }
        }
        // section check
        int rowSection = nextUnassigned[0] / 3;
        int colSection = nextUnassigned[1] / 3;
        for(int row = 3 * rowSection; row < 3 * (rowSection + 1); row++) {
            for(int col = 3 * colSection; col < 3 * (colSection + 1); col++) {
                if(dataStructure[row][col] == option) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean hasUnassignedCell(int[][] dataStructure, int[] nextUnassigned) {
        for(int row = 0; row < dataStructure.length; row++) {
            for(int col = 0; col < dataStructure[row].length; col++) {
                if(dataStructure[row][col] == 0) {
                    nextUnassigned[0] = row;
                    nextUnassigned[1] = col;
                    return true;
                }
            }
        }
        return false;
    }

    private static void print(int[][] dataStructure) {
        for(int row = 0; row < dataStructure.length; row++) {
            for(int col = 0; col < dataStructure[row].length; col++) {
                System.out.print(dataStructure[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println("*****************");
    }

    public static void main(String[] args) {
        int[][] dataStructure = new int[9][9];
        // row 0
        dataStructure[0][0] = 3;
        dataStructure[0][2] = 6;
        dataStructure[0][3] = 5;
        dataStructure[0][5] = 8;
        dataStructure[0][6] = 4;
        // row 1
        dataStructure[1][0] = 5;
        dataStructure[1][1] = 2;
        // row 2
        dataStructure[2][1] = 8;
        dataStructure[2][2] = 7;
        dataStructure[2][7] = 3;
        dataStructure[2][8] = 1;
        // row 3
        dataStructure[3][2] = 3;
        dataStructure[3][4] = 1;
        dataStructure[3][7] = 8;
        // row 4
        dataStructure[4][0] = 9;
        dataStructure[4][3] = 8;
        dataStructure[4][4] = 6;
        dataStructure[4][5] = 3;
        dataStructure[4][8] = 5;
        // row 5
        dataStructure[5][1] = 5;
        dataStructure[5][4] = 9;
        dataStructure[5][6] = 6;
        // row 6
        dataStructure[6][0] = 1;
        dataStructure[6][1] = 3;
        dataStructure[6][6] = 2;
        dataStructure[6][7] = 5;
        // row 7
        dataStructure[7][7] = 7;
        dataStructure[7][8] = 4;
        // row 8
        dataStructure[8][2] = 5;
        dataStructure[8][3] = 2;
        dataStructure[8][5] = 6;
        dataStructure[8][6] = 3;

        print(dataStructure);
        solve(dataStructure);
        print(dataStructure);
    }
}
