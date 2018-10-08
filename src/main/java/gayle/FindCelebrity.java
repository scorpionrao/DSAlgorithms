package gayle;

public class FindCelebrity {

    static boolean knows(int[][] matrix, int strangerRow, int celebrityCol) {
        return matrix[strangerRow][celebrityCol] == 1;
    }

    /*
        Returns ID (row) of celebrity
     */
    static int findCelebrity(int[][] matrix) {

        int stranger = 0;
        int celebrity = matrix.length - 1;

        // until stranger and celebrity meet
        while(stranger < celebrity) {
            if(knows(matrix, stranger, celebrity)) {
                // stranger fails to be a celebrity
                stranger++;
            } else {
                // very likely celebrity is farther.
                celebrity--;
            }
        }

        for(int i = 0; i < matrix.length; i++) {
            boolean diagonal = i != stranger;
            boolean strangerKnowsSomeone = knows(matrix, stranger, i);
            boolean someOneKnowsStranger = knows(matrix, i, stranger);

            if(diagonal && (strangerKnowsSomeone || !someOneKnowsStranger)) {
                // Stop
                return -1;
            }
        }
        return stranger;
    }

    public static void main(String[] args) {

        int[][] matrix = {
                            { 0, 0, 1, 0 },
                            { 0, 0, 1, 0 },
                            { 0, 0, 0, 0 },
                            { 0, 0, 1, 0 }
                         };
        System.out.println(findCelebrity(matrix));
    }
}
