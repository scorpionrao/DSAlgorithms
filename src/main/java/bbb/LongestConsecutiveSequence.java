package bbb;

import java.util.*;

public class LongestConsecutiveSequence {

    /*
        Time: O(N^2) for list, O(N^3) for array because of contains look up
        Space: O(1)
    */
    /* Worst Case: Array in Descending Order */
    private static int longestConsecutiveSequenceApproach1(List<Integer> list) {
        int maxLength = 0;
        for(int num : list) {
            int count = 0;
            while(list.contains(num++)) {
                count++;
            }
            maxLength = Math.max(maxLength, count);
        }
        return maxLength;
    }

    /* Time: O(N log N), Space: O(1) */
    private static int longestConsecutiveSequenceApproach2(List<Integer> list) {
        Collections.sort(list);
        int maxLength = 0;
        int count = 1;
        for(int i = 0; i < list.size() - 1; i++) {
            if(list.get(i).intValue() == list.get(i+1).intValue()) {
                continue;
            } else if(list.get(i).intValue() + 1 == list.get(i+1).intValue()) {
                count++;
            } else {
                maxLength = Math.max(maxLength, count);
                count = 1;
            }
        }
        maxLength = Math.max(maxLength, count);
        return maxLength;
    }

    /* Time: O(N), Space: O(1) */
    private static int longestConsecutiveSequenceApproach3(List<Integer> list) {

        int maxLength = 0;
        Set<Integer> set = new HashSet<>();
        for(Integer num : list) {
            set.add(num);
        }
        for(Integer unique : set) {
            if(set.contains(unique-1)) {
                // Unique is a subset of unique-1
                continue;
            }
            int count = 0;
            while(set.contains(unique++)) {
                count++;
            }
            maxLength = Math.max(maxLength, count);
        }
        return maxLength;
    }

    private static void evaluate(List<Integer> dups) {
        System.out.println(dups.toString());
        int maxLength1 = longestConsecutiveSequenceApproach1(dups);
        System.out.println("Approach1   : " + maxLength1);
        int maxLength2 = longestConsecutiveSequenceApproach2(dups);
        System.out.println("Approach2   : " + maxLength2);
        int maxLength3 = longestConsecutiveSequenceApproach3(dups);
        System.out.println("Approach3   : " + maxLength3);
    }
    public static void main(String[] args) {
        List<Integer> dups1 = Arrays.asList(new Integer[]{4, 2, 1, 6, 5});
        evaluate(dups1);
        System.out.println("*********************");
        List<Integer> dups2 = Arrays.asList(new Integer[]{5, 5, 3, 1});
        evaluate(dups2);
    }
}
