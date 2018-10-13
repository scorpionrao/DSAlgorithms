package gayle;

public class FindCelebrity {

    static boolean knows(int[][] matrix, int strangerRow, int celebrityCol) {
        return matrix[strangerRow][celebrityCol] == 1;
    }

    /*
        Returns ID (row) of celebrity
     */
    static int findCelebrity1(int[][] matrix) {

        int row = 0;
        int col = matrix.length - 1;

        /* until row meets column, one of the elements in diagonal */
        while(row < col) {
            printMatrix(matrix, row, col);
            if(knows(matrix, row, col)) {
                row++;
            } else {
                col--;
            }
        }

        for(int i = 0; i < matrix.length; i++) {
            boolean diagonal = i != row;
            boolean rowKnowsCelebrity = knows(matrix, row, i);
            boolean celebrityNotKnowsCol = !knows(matrix, i, row);

            if(diagonal && (rowKnowsCelebrity || celebrityNotKnowsCol)) {
                // Stop
                return -1;
            }
        }
        return row;
    }

    /*
        Time complexity - O(N^2)
     */
    static int findCelebrity2(int[][] input) {

        if(input == null) {
            throw new NullPointerException();
        }

        int[] rowAggregate = new int[input.length];
        int[] colAggregate = new int[input.length];

        for(int row = 0; row < input.length; row++) {
            for(int col = 0; col < input[row].length; col++) {
                rowAggregate[row] = rowAggregate[row] + input[row][col];
                colAggregate[col] = colAggregate[col] + input[row][col];
            }
        }

        for(int i = 0; i < input.length; i++) {
            if(rowAggregate[i] == 0 && colAggregate[i] == input.length - 1) {
                return i;
            }
        }

        return -1;
    }

    static void printMatrix(int[][] matrix, int row, int col) {
        for(int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if(row == i && col == j) {
                    System.out.print(" *" + matrix[i][j] + "* ");
                } else {
                    System.out.print("  " + matrix[i][j] + "  ");
                }
            }
            System.out.println();
        }
        System.out.println("-----------------------");
    }

    public static void main(String[] args) {

        int[][] matrix = {
                            { 0, 0, 1, 0 },
                            { 0, 0, 1, 0 },
                            { 0, 0, 0, 0 },
                            { 0, 0, 1, 0 }
                         };
        System.out.println(findCelebrity1(matrix));
    }
}
