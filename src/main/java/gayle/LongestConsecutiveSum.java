package gayle;

public class LongestConsecutiveSum {

    /*
        Time Complexity - O(N)
        Space Complexity - O(1)
    */
    public static int longestConsecutiveSum(int[] input) {
        if(input == null) {
            throw new NullPointerException();
        }

        int maxSum = 0;
        int currentSum = 0;
        for(int i = 0; i < input.length; i++) {
            if(input[i] < 0) {
                currentSum = 0;
            } else {
                currentSum = currentSum + input[i];
                if(currentSum > maxSum) {
                    maxSum = currentSum;
                }
            }
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] input1 = {3, 5, -1, 4, 3, -4, -3, 7, 2, 1};
        System.out.println("Longest Consecutive Sum = " + longestConsecutiveSum(input1));
        int[] input2 = {3, 5, 1, 4, 3, 4, 3, 7, 2, 1};
        System.out.println("Longest Consecutive Sum = " + longestConsecutiveSum(input2));
        int[] input3 = {-3, -5, -1, -4, -3, -4, -3, -7, -2, -1};
        System.out.println("Longest Consecutive Sum = " + longestConsecutiveSum(input3));
    }
}
