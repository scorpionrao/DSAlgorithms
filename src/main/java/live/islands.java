package live;

/*
 * To execute Java, please define "static void main" on a class
 * named ClosestXdestinations.
 *
 * If you need more classes, simply define them inline.
 */

/*
For example

1 1 1 0
0 1 0 0
0 0 0 0 = 3
1 0 1 0

0 1 1 0
0 1 0 0
0 0 0 0 = 2
1 1 1 0

*/
class Islands
{

    public static void dfs(int[][] matrix) {
        int numOfIslands = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                if(matrix[i][j] == 1) {
                    // do explore
                    explore(matrix, i, j);
                    numOfIslands++;
                }
            }
        }
        System.out.println(numOfIslands);
    }

    private static void explore(int[][] matrix, int i, int j) {
        // boundaries
        if(i<0 || j<0 || i>=matrix.length || j>=matrix[0].length || matrix[i][j] != 1) {
            return;
        }

        // visited
        matrix[i][j] = 0;
        explore(matrix, i+1, j);
        explore(matrix, i-1, j);
        explore(matrix, i, j+1);
        explore(matrix, i, j-1);

        if(i == 0 || i == matrix.length - 1) {
            explore(matrix, matrix.length - 1 - i, j);
        }

        if(j == 0 || j == matrix[i].length - 1) {
            explore(matrix, i, matrix[i].length - 1 - j);
        }
    }

    public static void main(String[] args) {
    /*
For example

0 1 1 0
0 1 0 0
0 0 0 0 = 1
1 0 1 1

0 1 1 0
0 1 0 0
0 0 0 0 = 2
1 1 1 0
*/

        int[][] matrix1 = {{0,1,1,0},{0,1,0,0},{0,0,0,0},{1,0,1,1}};
        dfs(matrix1);

        int[][] matrix2 = {{0,1,1,0},{0,1,0,0},{0,0,0,0},{1,1,1,0}};
        dfs(matrix2);


    }
}

