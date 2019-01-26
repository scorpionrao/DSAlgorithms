package leetcode;

import java.util.Arrays;

public class MaxDistanceOfClosestPerson {

    /* Time : O(3*N), Space : O(3*N) */
    private static int maxDistanceOfClosestPerson1(int[] seats) {

        int[] leftDistance = new int[seats.length];
        Arrays.fill(leftDistance, seats.length);
        for(int i = 0; i < seats.length; i++) {
            if(seats[i] == 1) {
                leftDistance[i] = 0;
            } else if(i > 0) {
                leftDistance[i] = leftDistance[i - 1] + 1;
            }
        }

        int[] rightDistance = new int[seats.length];
        Arrays.fill(rightDistance, seats.length);
        for(int i = seats.length-1; i >= 0; i--) {
            if(seats[i] == 1) {
                rightDistance[i] = 0;
            } else if(i < seats.length - 1) {
                rightDistance[i] = rightDistance[i+1] + 1;
            }
        }

        int maxDistance = 0;
        for(int i = 0; i < seats.length; i++) {
            if(seats[i] == 0) {
                maxDistance = Math.max(maxDistance, Math.min(leftDistance[i], rightDistance[i]));
            }
        }
        return maxDistance;
    }

    /* Time : O(2*N), Space : O(1) */
    private static int maxDistanceOfClosestPerson2(int[] seats) {

        int prevIndex = -1, future = 0;
        int maxDistance = 0;
        for(int i = 0; i < seats.length; i++) {
            if(seats[i] == 1) {
                prevIndex = i;
                future = i + 1;
            } else {
                while(future < seats.length && seats[future] == 0) {
                    future++;
                }
                int left = i - prevIndex;
                int right = future - i;
                maxDistance = Math.max(maxDistance, Math.min(left, right));
            }
        }
        return maxDistance;
    }

    private static void evaluate(int[] seats, int expected) {
        System.out.println(Arrays.toString(seats));
        int result1 = maxDistanceOfClosestPerson1(seats);
        System.out.println("Approach 1 : Expected = " + expected + ", Actual = " + result1);
        int result2 = maxDistanceOfClosestPerson2(seats);
        System.out.println("Approach 2 : Expected = " + expected + ", Actual = " + result2);
    }

    public static void main(String[] args) {
        int[] seats1 = {1, 0, 0, 0, 1, 0, 1};
        evaluate(seats1, 2);
    }
}
