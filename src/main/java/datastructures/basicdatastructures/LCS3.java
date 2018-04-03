package datastructures.basicdatastructures;

import java.util.*;

/*

    https://github.com/danuzclaudes/COMP-550-AlgorithmAnalysis

    Longest Common Subsequence

    Find the length of longest common subsequence

    Largest non-negative integer p such that ai1 = bj1 = ck1, etc...
 */
public class LCS3 {

    /*
            Key Idea:
            Subproblem: given three subsequences ending by Ai, Bj, Ck which has optimal length of LCS(i, j, k)
                        Either Ai = Bj = Ck or not.
                                            {
                                            {   if Ai=Bj=Ck,    LCS(i-1, j-1, k-1) + 1
                                            {
                                            {                {  LCS(i-1, j, k)
            Recurrence: LCS(i, j, k) = max  {   if not, max  {  LCS(i, j-1, k)
                                            {                {  LCS(i, j, k-1)
                                            {

            Bottom-up:  build up from 3D diagonal
            BZ: traverse all sub-cases of i=j, i=k, j=k, reduce the size of only one subproblem
     */
    private static int lcs3(int[] a, int[] b, int[] c) {
        int[][][] table = new int[a.length+1][b.length+1][c.length+1];
        for(int i=0; i<=a.length; i++) {
            for(int j=0; j<=b.length; j++) {
                for(int k=0; k<=c.length; k++) {
                    if(i == 0 || j == 0 || k == 0) {
                        table[i][j][k] = 0;
                        continue;
                    }
                    if(a[i-1] == b[j-1] && b[j-1] == c[k-1]) {
                        table[i][j][k] = table[i-1][j-1][k-1] + 1;
                        continue;
                    }
                    // take max from either of 3 cases
                    int x = table[i-1][j][k];
                    int y = table[i][j-1][k];
                    int z = table[i][j][k-1];
                    table[i][j][k] = Math.max(x, Math.max(y, z));
                }
            }
        }
        return table[a.length][b.length][c.length];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int an = scanner.nextInt();
        int[] a = new int[an];
        for (int i = 0; i < an; i++) {
            a[i] = scanner.nextInt();
        }
        int bn = scanner.nextInt();
        int[] b = new int[bn];
        for (int i = 0; i < bn; i++) {
            b[i] = scanner.nextInt();
        }
        int cn = scanner.nextInt();
        int[] c = new int[cn];
        for (int i = 0; i < cn; i++) {
            c[i] = scanner.nextInt();
        }
        System.out.println(lcs3(a, b, c));
    }
}

