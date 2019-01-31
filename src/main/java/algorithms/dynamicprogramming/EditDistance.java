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

    public static int[][] editDistance(String word1, String word2) {

        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                if(i == 0) {
                    dp[i][j] = j;
                } else if(j == 0) {
                    dp[i][j] = i;
                } else if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    int replace = dp[i-1][j-1];
                    int delete = dp[i][j-1];
                    int insert = dp[i-1][j];
                    dp[i][j] = 1 + Math.min(replace,
                            Math.min(insert, delete));
                }
            }
        }
        return dp;
    }

    public static boolean isOneEditDistance(String word1, String word2) {
        for(int i = 0; i < Math.min(word1.length(), word2.length()); i++) {
            if(word1.charAt(i) != word2.charAt(i)) {
                if(word1.length() == word2.length()) {
                    return word1.substring(i+1).equals(word2.substring(i+1));
                } else if(word1.length() > word2.length()) {
                    return word1.substring(i+1).equals(word2.substring(i));
                } else {
                    return word1.substring(i).equals(word2.substring(i+1));
                }
            }
        }
        return Math.abs(word1.length() - word2.length()) == 1;
    }

    public static int minDistanceOnlyDeletion(String word1, String word2) {

        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[i].length; j++) {
                if(i == 0) {
                    dp[i][j] = j;
                } else if(j == 0) {
                    dp[i][j] = i;
                } else if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        String source = "BEAD";
        String destination = "BEED";
        int[][] distance = editDistance(source, destination);
        System.out.println("Edit Distance : " + distance[distance.length-1][distance[0].length-1]);
        System.out.println("Only Deletion : " + minDistanceOnlyDeletion(source, destination));
        System.out.println("Is One Edit Distance : " + isOneEditDistance(source, destination));
    }
}
