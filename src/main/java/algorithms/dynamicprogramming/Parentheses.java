package algorithms.dynamicprogramming;

public class Parentheses {

    private static int[] MinAndMax(int[][] M, int[][] m, int i, int j) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for(int k = i; k < j; k++) {
            // int a = M[i, k] op(k) M(k+1, j);
            // int b = M[i, k] op(k) m(k+1, j);
            // int c = m[i, k] op(k) M(k+1, j);
            // int d = m[i, k] op(k) m(k+1, j);
            // min = min(min, a, b, c, d)
            // max = max(max, a, b, c, d)
        }
        return new int[] {min, max};
    }

    private static int[][] Parentheses(int[] digits, int[] operations) {
        int[][] M = new int[digits.length][digits.length];
        int[][] m = new int[digits.length][digits.length];
        for(int i = 0; i < digits.length; i++) {
            m[i][i] = digits[i];
            M[i][i] = digits[i];
        }
        for(int s = 1; s < digits.length; s++) {
            for(int i = 1; i < digits.length - s; i++) {
                int j = i + s;
                int[] result = MinAndMax(M, m, i, j);
                m[i][j] = result[0];
                M[i][j] = result[1];
            }
        }
        return M;
    }

    public static void main(String[] args) {

    }
}
