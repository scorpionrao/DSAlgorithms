package leetcode;

import java.util.Arrays;

public class Heaters {

    /*
        N - houses
        M - heaters
        Sort: M log M
        Binary Search: N log M
        Linear Search: N * M

        Total: (M * log M) + (N * log M) + (N * M)

        Space: O(1)
     */
    public static int findRadius(int[] houses, int[] heaters) {

        Arrays.sort(heaters);
        int maxOfMinDistance = 0;
        for(int house : houses) {
            int nearestHeater = Arrays.binarySearch(heaters, house);
            int result = 0;
            if(nearestHeater >= 0) {
                result = 0;
            } else {
                int i;
                for(i = 0; i < heaters.length; i++) {
                    if(heaters[i] > house) {
                        int rightDistance = heaters[i] - house;
                        if(i == 0) {
                            result = rightDistance;
                        } else {
                            int leftDistance = house - heaters[i-1];
                            result = Math.min(rightDistance, leftDistance);
                        }
                        break;
                    }
                }
                if(i == heaters.length) {
                    result = house - heaters[i-1];
                }
            }
            maxOfMinDistance = Math.max(maxOfMinDistance, result);
        }
        return maxOfMinDistance;
    }

    public static void main(String[] args) {
        int[] houses = {1, 2, 3};
        int[] heaters = {2};
        int result = findRadius(houses, heaters);
        System.out.println("Result - " + result);
    }
}
