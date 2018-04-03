package algorithms.dynamicprogramming;

import java.util.*;

/**
 * Compute the Edit Distance between two strings
 *
 * Edit distance = min of insertions, deletions and mismatches of 2 strings
 *
 * Example: shorts, ports -> 3
 * Example: editing, distance -> 5
 */
class EditDistanceSubmission {

  /*
    Key Idea : None

    Subproblem: given an optimal alignment of 2 strings, last letter
                can be insertion (extra B[j]), deletion, mismatch

    Bottom-up: D[i,j] = min {
        D[i-1][j] + 1
        D[i,j-1] + 1
        D[i-1][j-1] + 1 if mismatch
        D[i-1][j-1] if match

        build all 3 before calculating
    }
   */
  public static int editDistance(String s, String t) {
    int m = s.length(), n = t.length();
    // BZ: match "" with the other string
    int[][] table = new int[m+1][n+1];
    // first row/col as i/j distance from ""
    for(int j = 1; j <= n; j++) {
      table[0][j] = j;
    }
    for(int i = 1; i <= m; i++) {
      table[i][0] = i;
    }
    // bottom-up from smaller subproblems
    for(int i = 1; i <= m; i++) {
      for(int j = 1; j <= n; j++) {
        int insertion = table[i][j-1] + 1;
        int deletion = table[i-1][j] + 1;
        int mismatch = table[i-1][j-1] + (s.charAt(i-1) == t.charAt(j-1) ? 0 : 1);
        table[i][j] = Math.min(insertion, Math.min(deletion, mismatch));
      }
    }
    return table[m][n];
  }
  public static void main(String args[]) {
    Scanner scan = new Scanner(System.in);

    String s = scan.next();
    String t = scan.next();

    System.out.println(editDistance(s, t));
    scan.close();
  }

}
