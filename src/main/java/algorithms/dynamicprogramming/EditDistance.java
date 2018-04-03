package algorithms.dynamicprogramming;

/*
    Input: Two strings
    Output: Minimum number of operations to transform one string to another

    Alignment Score = # matches - (mean * # mismatches) - (std dev * # indels)

    Edit Distance: mean = std dev = 0

    Edit Distance = # matches

    Maximizing Alignment Score = Minimizing Edit Distance

    Eg: Longest common subsequence. Confirm in geeksforgeeks

    D(i,j) = Min of

    D(i, j-1) + 1       - Insert in A
    D(i-1, j) + 1       - Insert in B (deletion)
    D(i-1, j-1) + 1     - Mismatch
    D(i-1, j-1)         - Equal

    Total num of symbols = Alignment score (0, 1/2) + Edit distance

 */
public class EditDistance {

    private static int[][] editDistance(char[] arrayA, char[] arrayB) {

        int[][] distance = new int[arrayA.length + 1][arrayB.length + 1];
        for(int i = 0; i < distance.length; i++) {
            distance[i][0] = i;
        }
        for(int j = 0; j < distance[0].length; j++) {
            distance[0][j] = j;
        }
        for(int i = 1; i < distance.length; i++) {
            for(int j = 1; j < distance[0].length; j++) {
                int insertion = distance[i][j-1] + 1;
                int deletion = distance[i-1][j] + 1;
                int match = distance[i-1][j-1];
                int mismatch = distance[i-1][j-1] + 1;
                int inDels = Math.min(insertion, deletion);
                if(arrayA[i-1] == arrayB[j-1]) {
                    distance[i][j] = Math.min(inDels, match);
                } else {
                    distance[i][j] = Math.min(inDels, mismatch);
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        char[] arrayA = "BREAD".toCharArray();
        char[] arrayB = "REALLY".toCharArray();
        int[][] distance = editDistance(arrayA, arrayB);
        for(int i = 0; i < distance.length; i++) {
            for (int j = 0; j < distance[0].length; j++) {
                System.out.print(distance[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*
        First if checks all the ways when the last action is to delete the last symbol.
        Second if checks all the ways when the last action is to insert the necessary symbol.
        Third if checks all the ways to match last symbols of the prefixes.
        Last if checks all the ways to replace the last symbol of the i-th prefix of the first word by the last symbol of the j-th prefix of the second word.

        ways[i, j] = 0
        // all ways when last action was delete last symbol
        if T[i, j] == T[i - 1, j] + 1:
          ways[i, j] += ways[i - 1, j]
        // all ways when last action was insert necessary symbol
        if T[i, j] == T[i, j - 1] + 1:
          ways[i, j] += ways[i, j - 1]
        // all ways to match last symbols of prefixes
        if word1[i] == word2[j] and T[i, j] == T[i - 1, j - 1]:
          ways[i, j] += ways[i - 1, j - 1]
        // all ways to replace ith letter in first word with jth letter in second word
        if T[i, j] == T[i - 1, j - 1] + 1:
          ways[i, j] += ways[i - 1, j - 1]
     */
    private static char[] outputAlignment(int[][] distance, int[] arrayA, int[] arrayB, int i, int j) {
        if (i == 0 && j == 0) {
            return null;
        }
        // deletion
        if(i > 0 && distance[i][j] == distance[i-1][j] + 1) {
            outputAlignment(distance, arrayA, arrayB, i-1, j);
            System.out.println(arrayA[i]);
            System.out.println("-");
        } // insertion
        else if(j > 0 && distance[i][j] == distance[i][j-1] + 1) {
            outputAlignment(distance, arrayA, arrayB, i, j-1);
            System.out.println("-");
            System.out.println(arrayB[j]);
        } else {
            outputAlignment(distance, arrayA, arrayB, i-1, j-1);
            System.out.println(arrayA[i]);
            System.out.println(arrayB[j]);
        }
        return null;
    }
}
